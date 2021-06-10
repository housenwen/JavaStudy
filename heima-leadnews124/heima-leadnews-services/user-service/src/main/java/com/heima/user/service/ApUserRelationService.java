package com.heima.user.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dto.UserRelationDto;

public interface ApUserRelationService {
    ResponseResult follow(UserRelationDto dto);
}