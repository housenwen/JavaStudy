package com.heima.api.wemedia;

import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.media.pojos.WmUser;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoginControllerApi {

    /**
     * 自媒体登录
     * @param user
     * @return
     */
    public ResponseResult login(@RequestBody WmUser user);
}
