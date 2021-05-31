package com.heima.api.wemedia;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.dtos.WmMaterialDto;
import com.heima.model.media.dtos.WmMaterialListDto;
import org.springframework.web.multipart.MultipartFile;

public interface MaterialManageControllerApi {

    /**
     * 上传图片
     * @param multipartFile
     * @return
     */
    ResponseResult uploadPicture(MultipartFile multipartFile);

    /**
     * 素材列表
     * @param dto
     * @return
     */
    ResponseResult list(WmMaterialListDto dto);

    /**
     * 删除图片
     * @param wmMaterial
     * @return
     */
    ResponseResult delPicture(WmMaterialDto wmMaterial);

    /**
     * 取消收藏
     * @param dto
     * @return
     */
    ResponseResult cancleCollectionMaterial(WmMaterialDto dto);

    /**
     * 收藏图片
     * @param dto
     * @return
     */
    ResponseResult collectionMaterial(WmMaterialDto dto);

}