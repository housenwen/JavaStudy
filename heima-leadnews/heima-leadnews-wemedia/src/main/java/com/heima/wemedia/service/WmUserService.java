package com.heima.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 登录
     * @param wmUser
     * @return
     */
    public ResponseResult login(WmUser wmUser);
}