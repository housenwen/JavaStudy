package com.heima.feigns.wemedia;


import com.heima.config.HeimaFeignAutoConfiguration;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.wemedia.pojo.WmNews;
import com.heima.model.wemedia.pojo.WmUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "leadnews-wemedia",configuration = HeimaFeignAutoConfiguration.class)
public interface WemediaFeign {
    @GetMapping("/api/v1/news/findOne/{id}")
    WmNews findById(@PathVariable("id") Integer id);
    @PutMapping("/api/v1/news/update")
    ResponseResult updateWmNews(@RequestBody WmNews wmNews);
    @GetMapping("/api/v1/user/findOne/{id}")
    WmUser findWmUserById(@PathVariable("id") Integer id);


    @GetMapping("/api/v1/news/findRelease")
    List<Integer> findRelease();

    @PostMapping("/api/v1/user/save")
     WmUser save(@RequestBody WmUser wmUser);
    @GetMapping("/api/v1/user/findByName/{name}")
     WmUser findByName(@PathVariable("name") String name);
}
