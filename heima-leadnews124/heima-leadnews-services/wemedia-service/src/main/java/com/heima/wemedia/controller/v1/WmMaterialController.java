package com.heima.wemedia.controller.v1;
import com.heima.common.constants.wemedia.WemediaConstants;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dto.WmMaterialDto;
import com.heima.wemedia.service.WmMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@Api(value = "媒体素材管理API",tags = "媒体素材管理API")
@RestController
@RequestMapping("/api/v1/material")
public class WmMaterialController{
    @Autowired
    WmMaterialService wmMaterialService;
    @ApiOperation("上传素材")
    @PostMapping("/upload_picture")
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        return wmMaterialService.uploadPicture(multipartFile);
    }
    @ApiOperation("查询素材列表")
    @PostMapping("/list")
    public ResponseResult findList(@RequestBody WmMaterialDto dto) {
        return wmMaterialService.findList(dto);
    }

    @ApiOperation("删除素材")
    @GetMapping("/del_picture/{id}")
    public ResponseResult delPicture(@PathVariable("id") Integer id) {
        return wmMaterialService.delete(id);
    }

    @ApiOperation("取消收藏素材")
    @GetMapping("/cancel_collect/{id}")
    public ResponseResult cancelCollectionMaterial(@PathVariable("id") Integer id) {
        return wmMaterialService.updateStatus(id, WemediaConstants.CANCEL_COLLECT_MATERIAL);
    }
    @ApiOperation("收藏素材")
    @GetMapping("/collect/{id}")
    public ResponseResult collectionMaterial(@PathVariable("id") Integer id) {
        return wmMaterialService.updateStatus(id,WemediaConstants.COLLECT_MATERIAL);
    }
}