package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dto.WmMaterialDto;
import com.heima.model.wemedia.pojo.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

public interface WmMaterialService extends IService<WmMaterial> {
    /**
     * 上传图片接口
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPicture(MultipartFile multipartFile);

    ResponseResult findList(WmMaterialDto dto);

    ResponseResult delete(Integer id);

    ResponseResult updateStatus(Integer id, Short status);
}
