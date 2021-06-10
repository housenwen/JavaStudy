package com.heima.user.service;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.user.dto.LoginDto;

public interface ApUserLoginService {
    ResponseResult login(LoginDto dto);
}
