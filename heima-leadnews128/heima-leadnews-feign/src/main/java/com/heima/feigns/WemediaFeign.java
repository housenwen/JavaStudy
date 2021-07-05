package com.heima.feigns;
import com.heima.config.HeimaFeignAutoConfiguration;
import com.heima.feigns.fallback.WemediaFeignFallback;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojos.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient( // 标记当前接口 是一个feign的客户端
        value = "leadnews-wemedia", // 服务名称
        fallbackFactory = WemediaFeignFallback.class, // 服务降级工厂对象
        configuration = HeimaFeignAutoConfiguration.class // feign的配置类
)
public interface WemediaFeign {
    @PostMapping("/api/v1/user/save")
    public ResponseResult<WmUser> save(@RequestBody WmUser wmUser);
    @GetMapping("/api/v1/user/findByName/{name}")
    public ResponseResult<WmUser> findByName(@PathVariable("name") String name);
}