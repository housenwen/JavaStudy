package com.heima.admin.controller.v1;

import com.heima.admin.service.AdSensitiveService;
import com.heima.api.admin.SensitiveControllerApi;
import com.heima.model.admin.dtos.SensitiveDto;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/sensitive")
public class SensitiveController implements SensitiveControllerApi {

    @Autowired
    private AdSensitiveService sensitiveService;

    @Override
    @PostMapping("/list")
    public ResponseResult findByNameAndPage(@RequestBody SensitiveDto dto) {
        return sensitiveService.findByNameAndPage(dto);
    }

    @Override
    @PostMapping("/save")
    public ResponseResult save(@RequestBody AdSensitive sensitive) {
        return sensitiveService.insert(sensitive);
    }

    @Override
    @PostMapping("/update")
    public ResponseResult update(@RequestBody AdSensitive sensitive) {
        return sensitiveService.update(sensitive);
    }

    @Override
    @GetMapping("/del/{id}")
    public ResponseResult delete(@PathVariable("id") Integer id) {
        return sensitiveService.delete(id);
    }
    
}