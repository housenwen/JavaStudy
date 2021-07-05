package com.heima.feigns.fallback;

import com.heima.feigns.WemediaFeign;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.pojos.WmUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2021/7/5 10:43
 **/
@Component
@Slf4j
public class WemediaFeignFallback implements FallbackFactory<WemediaFeign> {
    // 和直接实现接口对比，这个方法多个异常对象
    @Override
    public WemediaFeign create(Throwable throwable) {
        throwable.printStackTrace();
        return new WemediaFeign() {
            @Override
            public ResponseResult<WmUser> save(WmUser wmUser) {
                log.info("调用参数: {}",wmUser);
                log.error("WemediaFeign  save 远程调用出现异常==> {}",throwable.getMessage());
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"远程调用出现异常");
            }

            @Override
            public ResponseResult<WmUser> findByName(String name) {
                log.info("调用参数: {}",name);
                log.error("WemediaFeign  findByName 远程调用出现异常==> {}",throwable.getMessage());
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR,"远程调用出现异常");
            }
        };
    }
}
