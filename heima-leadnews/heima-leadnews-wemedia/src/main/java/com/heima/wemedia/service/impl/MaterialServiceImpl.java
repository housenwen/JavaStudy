package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.fastdfs.FastDFSClient;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.dtos.WmMaterialDto;
import com.heima.model.media.dtos.WmMaterialListDto;
import com.heima.model.media.pojos.WmMaterial;
import com.heima.model.media.pojos.WmNewsMaterial;
import com.heima.model.media.pojos.WmUser;
import com.heima.utils.threadlocal.WmThreadLocalUtils;
import com.heima.wemedia.mapper.WmMaterialMapper;
import com.heima.wemedia.mapper.WmNewsMaterialMapper;
import com.heima.wemedia.service.MaterialService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MaterialServiceImpl extends ServiceImpl<WmMaterialMapper, WmMaterial> implements MaterialService {

    @Value("${fdfs.url}")
    private String fileServerUrl;

    @Autowired
    private FastDFSClient fastDFSClient;

    @Override
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        //获取当前用户
        WmUser user = WmThreadLocalUtils.getUser();
        if (multipartFile == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        String fileId = null;
        //上传图片获得文件id
        try {
            fileId = fastDFSClient.uploadFile(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("user {} upload file {} to fastDFS error, error info:\n", user.getId(),multipartFile.getOriginalFilename(),e.getMessage());
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
        //上传成功保存媒体资源到数据库
        WmMaterial wmMaterial = new WmMaterial();
        wmMaterial.setCreatedTime(new Date());
        wmMaterial.setType( (short)0);
        wmMaterial.setUrl(fileId);
        wmMaterial.setUserId(user.getId().intValue());
        wmMaterial.setIsCollection((short) 0);
        save(wmMaterial);
        //设置返回值
        wmMaterial.setUrl(fileServerUrl + wmMaterial.getUrl());
        return ResponseResult.okResult(wmMaterial);
    }

    @Override
    public ResponseResult findList(WmMaterialListDto dto) {
        //检查参数
        dto.checkParam();
        Integer uid = WmThreadLocalUtils.getUser().getId();

        QueryWrapper<WmMaterial> queryWrapper = new QueryWrapper<WmMaterial>();
        //按照当前用户查询
        queryWrapper.lambda().eq(WmMaterial::getUserId,uid);
        //是否是收藏
        if(dto.getIsCollected()!=null && dto.getIsCollected().shortValue() == 1){
            queryWrapper.lambda().eq(WmMaterial::getIsCollection,dto.getIsCollected());
        }
        //按照日期倒序
        queryWrapper.lambda().orderByDesc(WmMaterial::getCreatedTime);
        //带条件分页查询
        IPage pageParam = new Page(dto.getPage(),dto.getSize());
        IPage pageData = page(pageParam, queryWrapper);
        List<WmMaterial> datas = pageData.getRecords();
        //为每个图片加上前缀  fileServerUrl + group1/M00/00/00/wKjIgl5rYHGAZXIeAAANyzMmUz0556.png
        datas = datas.stream().map((item) -> {
            item.setUrl(fileServerUrl + item.getUrl());
            return item;
        }).collect(Collectors.toList());
        Map<String, Object> resDatas = new HashMap<>();
        resDatas.put("curPage", dto.getPage());
        resDatas.put("size", dto.getSize());
        resDatas.put("list", datas);
        resDatas.put("total", pageData.getTotal());
        return ResponseResult.okResult(resDatas);
    }

    @Autowired
    private WmNewsMaterialMapper wmNewsMaterialMapper;

    @Override
    public ResponseResult delPicture(WmMaterialDto dto) {
        WmUser user = WmThreadLocalUtils.getUser();
        if (dto == null || dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //删除fastDFS上的文件
        WmMaterial wmMaterial = getById(dto.getId());
        if (wmMaterial == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //查询当前图片是否被文章所引用，如果应用则不能删除
        QueryWrapper<WmNewsMaterial> queryWrapper = new QueryWrapper<WmNewsMaterial>();
        queryWrapper.lambda().eq(WmNewsMaterial::getMaterialId,dto.getId());
        int count = wmNewsMaterialMapper.selectCount(queryWrapper);
        if (count > 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID, "当前图片被引用");
        }
        //去除前缀
        String fileId = wmMaterial.getUrl().replace(fileServerUrl, "");
        try {
            fastDFSClient.delFile(fileId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("user {} delete file {} from fastDFS error, error info:\n", user.getId(),
                    fileId, e.getMessage());
            return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
        }
        //删除数据库记录
        removeById(dto.getId());
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    @Override
    public ResponseResult changeUserMaterialStatus(WmMaterialDto dto, Short type) {
        if (dto == null || dto.getId() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        WmUser user = WmThreadLocalUtils.getUser();
        //更新收藏状态
        update(Wrappers.<WmMaterial>lambdaUpdate().set(WmMaterial::getIsCollection, type).eq(WmMaterial::getId,dto.getId()).eq(WmMaterial::getUserId,user.getId()));

        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

}