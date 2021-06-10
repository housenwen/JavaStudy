package com.heima.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.admin.dto.AdUserDto;
import com.heima.model.admin.pojo.AdUser;
import com.heima.model.common.dtos.ResponseResult;

public interface UserService extends IService<AdUser> {

    ResponseResult login(AdUserDto dto);
}
