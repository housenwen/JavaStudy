package com.heima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dto.SensitiveDto;
import com.heima.model.admin.pojo.AdSensitive;
import com.heima.model.common.dtos.ResponseResult;

public interface SensitiveService extends IService<AdSensitive> {
    ResponseResult list(SensitiveDto dto);
    ResponseResult insert(AdSensitive sensitive);
    ResponseResult update(AdSensitive sensitive);
    ResponseResult delete(Integer id);
}
