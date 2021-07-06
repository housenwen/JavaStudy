package com.heima.wemedia.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustException;
import com.heima.common.exception.CustomException;
import com.heima.file.service.FileStorageService;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.WmThreadLocalUtils;
import com.heima.model.wemedia.dtos.WmMaterialDto;
import com.heima.model.wemedia.pojos.WmMaterial;
import com.heima.model.wemedia.pojos.WmNewsMaterial;
import com.heima.model.wemedia.pojos.WmUser;
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
 * @创建日期 2021/7/6 15:48
 **/
@Service
@Slf4j
public class WmMaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements WmMaterialService {


    @Autowired
    FileStorageService fileStorageService;

    @Value("${file.oss.prefix}")
    String prefix;
    @Value("${file.oss.web-site}")
    String webSite;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        // 1. 校验参数  文件不能为空  文件size
        if(multipartFile == null || multipartFile.getSize() == 0){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"请上传正确的文件信息");
        }
        //== 校验文件格式是否正确   暂时支持后缀: [jpg  jpeg  png  gif ]

        //    校验用户是否登录
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            CustException.cust(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 2. 上传文件到OSS  path文件路径
        String originalFilename = multipartFile.getOriginalFilename(); // 原始的文件名称
        //    uuid + 文件后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().replaceAll("-","");
        fileName = fileName + suffix;
        try {
            fileName = fileStorageService.store(prefix, fileName, multipartFile.getInputStream());
            // 3. 封装素材实例对象
            WmMaterial wmMaterial = new WmMaterial();
            wmMaterial.setUserId(user.getId());// 登录用户
            wmMaterial.setUrl(fileName); // oss文件路径
            wmMaterial.setType((short)0);
            wmMaterial.setIsCollection((short)0);
            wmMaterial.setCreatedTime(new Date());
            // 4. 保存素材
            save(wmMaterial);
            // 5. 返回结果
            // 前台需要回显
            wmMaterial.setUrl(webSite + wmMaterial.getUrl());
            return ResponseResult.okResult(wmMaterial);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR,"文件上传失败，请稍后重试");
        }
    }

    @Override
    public ResponseResult findList(WmMaterialDto dto) {
        // 1 . 校验参数
       if(dto == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
       }
       dto.checkParam();
        WmUser user = WmThreadLocalUtils.getUser();
        if(user == null){
            CustException.cust(AppHttpCodeEnum.NEED_LOGIN);
        }
        // 2. 封装查询参数
        // 2.1  分页参数
        IPage pageReq = new Page(dto.getPage(),dto.getSize());
        // 2.2  查询条件      是否收藏    用户id     降序排序
        LambdaQueryWrapper<WmMaterial> queryWrapper = Wrappers.<WmMaterial>lambdaQuery();
        if(dto.getIsCollection()!=null && dto.getIsCollection().intValue() == 1){
            queryWrapper.eq(WmMaterial::getIsCollection,dto.getIsCollection());
        }
        queryWrapper.eq(WmMaterial::getUserId,user.getId()); // 设置当前登录用户
        queryWrapper.orderByDesc(WmMaterial::getCreatedTime); // 时间降序
        // 3. 执行得到结果
        IPage<WmMaterial> pageResult = page(pageReq, queryWrapper);
        List<WmMaterial> list = pageResult.getRecords();
        for (WmMaterial wmMaterial : list) {
            wmMaterial.setUrl(webSite + wmMaterial.getUrl());
        }
        // 遍历结果 添加访问前缀
        return new PageResponseResult(dto.getPage(),dto.getSize(),pageResult.getTotal(),list);
    }

    @Autowired
    WmNewsMaterialMapper wmNewsMaterialMapper;

    @Transactional
    @Override
    public ResponseResult delPicture(Integer id) {
        // 1. 校验参数
        if(id == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"素材id 不能为空");
        }
        // 2. 根据id查询素材
        WmMaterial wmMaterial = getById(id);
        if(wmMaterial == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"该素材不存在");
        }
        // 3. 判断当前素材是否被引用
        Integer count = wmNewsMaterialMapper.selectCount(Wrappers.<WmNewsMaterial>lambdaQuery().eq(WmNewsMaterial::getMaterialId, id));
        if(count>0){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"该素材已被引用，无法删除");
        }
        // 4. 删除素材信息
        removeById(id);
        // 5. 在oss中删除对应的素材图片信息
        fileStorageService.delete(wmMaterial.getUrl());
        return ResponseResult.okResult();
    }

    /**
     * @param id  素材的id
     * @param type 0 取消收藏    1 收藏
     * @return
     */
    @Override
    public ResponseResult updateStatus(Integer id, Short type) {
        // 1. 参数校验
        if(id == null ){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"id不能为空");
        }
        update(Wrappers.<WmMaterial>lambdaUpdate()
                        .set(WmMaterial::getIsCollection,type)
                        .eq(WmMaterial::getId,id)
        );
        return ResponseResult.okResult();
    }
}
