package com.heima.feigns.fallback;

import com.heima.feigns.WemediaFeign;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.model.wemedia.pojos.WmUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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
            public ResponseResult<List<Integer>> findRelease() {
                log.error("自媒体 WmNews findRelease 远程调用出错啦 ~~~ !!!! {} ",throwable);
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
            }

            @Override
            public ResponseResult<WmNews> findById(Integer id) {
                log.error("参数: {}",id);
                log.error("自媒体 WmNews findById 远程调用出错啦 ~~~ !!!! {} ",throwable);
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
            }
            @Override
            public ResponseResult updateWmNews(WmNews wmNews) {
                log.error("参数: {}",wmNews);
                log.error("自媒体 wmNews updateWmNews 远程调用出错啦 ~~~ !!!! {} ",throwable);
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
            }
            @Override
            public ResponseResult<WmUser> findWmUserById(Integer id) {
                log.error("参数: {}",id);
                log.error("自媒体 WmUser findWmUserById 远程调用出错啦 ~~~ !!!! {} ",throwable);
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
            }
            @Override
            public ResponseResult<WmUser> save(WmUser wmUser) {
                log.error("参数: {}",wmUser);
                log.error("自媒体 save 远程调用出错啦 ~~~ !!!! {} ",throwable.getMessage());
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
            }
            @Override
            public ResponseResult<WmUser> findByName(String name) {
                log.error("参数: {}",name);
                log.error("自媒体 findByName 远程调用出错啦 ~~~ !!!! {} ",throwable);
                return ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
            }
        };
    }
}
