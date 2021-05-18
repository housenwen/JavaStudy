package com.tanhua.server.service;

import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.dubbo.api.UserLocationApi;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class BaiduService {

    @DubboReference(version = "1.0.0")
    private UserLocationApi userLocationApi;

    public void updateLocation(Double longitude, Double latitude, String address) {
        Long userId = UserThreadLocal.get();
        this.userLocationApi.updateUserLocation(userId, longitude, latitude, address);
    }
}
