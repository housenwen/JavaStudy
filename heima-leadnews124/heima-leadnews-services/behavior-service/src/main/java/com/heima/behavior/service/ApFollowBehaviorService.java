package com.heima.behavior.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.behavior.dto.FollowBehaviorDto;
import com.heima.model.behavior.pojo.ApFollowBehavior;
import com.heima.model.common.dtos.ResponseResult;

public interface ApFollowBehaviorService extends IService<ApFollowBehavior> {
    public ResponseResult saveFollowBehavior(FollowBehaviorDto dto);
}