package com.heima.wemedia.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.file.service.FileStorageService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.WmThreadLocalUtils;
import com.heima.model.wemedia.dto.WmMaterialDto;
import com.heima.model.wemedia.pojo.WmMaterial;
import com.heima.model.wemedia.pojo.WmNewsMaterial;
import com.heima.model.wemedia.pojo.WmUser;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.service.WmMaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @作者 itcast
 * @创建日期 2021/5/26 10:52
 **/
@Service
@Slf4j
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {

    @Autowired
    FileStorageService fileStorageService;

    @Value("${file.oss.profix}")
    String profix;
    @Value("${file.oss.web-site}")
    String webSite;
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        //1. 检查文件是否存在
        if(multipartFile == null || multipartFile.getSize() == 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"文件不存在");
        }
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN,"登录后才能上传文件");
        }
        //2. 上传文件到oss
        try {
            String oriName = multipartFile.getOriginalFilename();
            String suffix = oriName.substring(oriName.lastIndexOf("."));// .jpg
            //     生成文件的名称
            String fileName = UUID.randomUUID().toString().replaceAll("-", "");
            //     截图原始文件的后缀
            //     使用工具类上传到oss
            String fileUrl = fileStorageService.store(profix, fileName + suffix, multipartFile.getInputStream());
            //3. 创建素材表信息，将图片路径存入
            WmMaterial wmMaterial = new WmMaterial();
            wmMaterial.setUserId(user.getId());
            wmMaterial.setUrl(fileUrl); // 设置oss中文件路径
            wmMaterial.setType((short)0);
            wmMaterial.setIsCollection((short)0);
            wmMaterial.setCreatedTime(new Date());
            //4. 将素材保存到数据库
            this.save(wmMaterial);
            wmMaterial.setUrl(webSite + fileUrl);
            //5. 封装返回结果
            return ResponseResult.okResult(wmMaterial);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传出现异常 ==> {}",e.getMessage());
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"文件上传失败");
        }
    }

    @Override
    public ResponseResult findList(WmMaterialDto dto) {
        // 1. 校验参数  分页
        dto.checkParam();
        // 2. 封装查询条件
        //     分页条件
        IPage<WmMaterial> pageReq = new Page<>(dto.getPage(),dto.getSize());
        LambdaQueryWrapper<WmMaterial> queryWrapper = Wrappers.<WmMaterial>lambdaQuery();
        //     判断收藏条件
        if(dto.getIsCollection()!=null && dto.getIsCollection().intValue() == 1){
            queryWrapper.eq(WmMaterial::getIsCollection,1);
        }
        //     获取当前登录用户
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        queryWrapper.eq(WmMaterial::getUserId,user.getId());
        //     发布时间降序
        queryWrapper.orderByDesc(WmMaterial::getCreatedTime);
        // 3. 执行查询
        IPage<WmMaterial> pageResult = this.page(pageReq, queryWrapper);
        //     给url路径加访问前缀路径
        List<WmMaterial> records = pageResult.getRecords();
        if(records != null && records.size()>0){
            for (WmMaterial record : records) {
                record.setUrl(webSite + record.getUrl());
            }
        }
        // 4. 封装结果返回
        PageResponseResult pageResponseResult = new PageResponseResult(dto.getPage(), dto.getSize(), pageResult.getTotal());
        pageResponseResult.setData(records);
        return pageResponseResult;
    }

    @Autowired
    WmNewsMaterialMapper wmNewsMaterialMapper;

    @Transactional
    @Override
    public ResponseResult delete(Integer id) {
        // 1.检查ID
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"id不能为空");
        }
        // 2.查询素材数据
        WmMaterial material = getById(id);
        if (material==null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"该素材不存在");
        }
        // 3.判断是否登录
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        if (!material.getUserId().equals(user.getId())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_ALLOW,"无权限操作");
        }
        // 4.查看素材是否引用
        Integer refCount = wmNewsMaterialMapper.selectCount(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getMaterialId, id));
        if(refCount > 0){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_ALLOW,"素材被引用，无法删除");
        }
        // 5. 删除素材
        removeById(id);
        // 6. 删除OSS中文件
        fileStorageService.delete(material.getUrl());
        return ResponseResult.okResult();
    }
    @Override
    public ResponseResult updateStatus(Integer id, Short status) {
        if(id == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 修改状态
        update(Wrappers.<WmMaterial>lambdaUpdate()
                .set(WmMaterial::getIsCollection,status)
                .eq(WmMaterial::getId,id)
                .eq(WmMaterial::getUserId,user.getId()));
        return ResponseResult.okResult();
    }

//    public static void main(String[] args) {
////        String file = "meinv/.../213..jpg";
////        String suffix = file.substring(file.lastIndexOf("."));// .jpg
//
//        System.out.println(UUID.randomUUID().toString());
//    }
}
