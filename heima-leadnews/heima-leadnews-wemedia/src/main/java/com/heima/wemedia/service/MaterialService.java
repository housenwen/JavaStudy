package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.dtos.WmMaterialDto;
import com.heima.model.media.dtos.WmMaterialListDto;
import com.heima.model.media.pojos.WmMaterial;
import org.springframework.web.multipart.MultipartFile;

public interface MaterialService extends IService<WmMaterial> {

    /**
     * 上传图片接口
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * 素材列表查询
     * @param dto
     * @return
     */
    ResponseResult findList(WmMaterialListDto dto);

    /**
     * 删除图片
     * @param dto
     * @return
     */
    ResponseResult delPicture(WmMaterialDto dto);

    /**
     * 收藏与取消收藏
     * @param dto
     * @param type
     * @return
     */
    ResponseResult changeUserMaterialStatus(WmMaterialDto dto, Short type);
}