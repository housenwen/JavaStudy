package com.heima.admin.controller.v1;

import com.heima.admin.service.AdSensitiveService;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@Api(value = "敏感词管理API",tags = "敏感词管理API")
@RestController
@RequestMapping("/api/v1/sensitive")
public class AdSensitiveController{
    @Autowired
    AdSensitiveService adSensitiveService;

    @ApiOperation(value = "敏感词查询")
    @PostMapping("/list")
    public ResponseResult list(@RequestBody SensitiveDto dto) {
        return adSensitiveService.list(dto);
    }
  
    @ApiOperation("敏感词添加")
    @PostMapping("/save")
    public ResponseResult insert(@RequestBody AdSensitive adSensitive) {
        return adSensitiveService.insert(adSensitive);
    }
  
    @ApiOperation("敏感词修改")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody AdSensitive adSensitive) {
        return adSensitiveService.update(adSensitive);
    }
  
    @ApiOperation("敏感词删除")
    @DeleteMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return adSensitiveService.delete(id);
    }
}