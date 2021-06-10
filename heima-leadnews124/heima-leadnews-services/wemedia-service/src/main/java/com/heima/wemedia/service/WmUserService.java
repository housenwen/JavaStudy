package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.dto.WmUserDto;
import com.heima.model.wemedia.pojo.WmUser;

public interface WmUserService extends IService<WmUser> {
    ResponseResult login(WmUserDto dto);
}
