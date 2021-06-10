package com.heima.behavior.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.behavior.dto.ReadBehaviorDto;
import com.heima.model.behavior.pojo.ApReadBehavior;
import com.heima.model.common.dtos.ResponseResult;

public interface ApReadBehaviorService extends IService<ApReadBehavior> {
    ResponseResult read(ReadBehaviorDto dto);
}
