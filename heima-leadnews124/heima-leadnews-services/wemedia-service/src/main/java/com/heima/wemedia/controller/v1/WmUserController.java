package com.heima.wemedia.controller.v1;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.model.wemedia.pojo.WmUser;
import com.heima.wemedia.service.WmUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "自媒体管理Api", tags = "自媒体管理Api")
@RestController
@RequestMapping("/api/v1/user")
public class WmUserController{
    @Autowired
    WmUserService wmUserService;
    @ApiOperation("保存自媒体用户")
    @PostMapping("/save")
    public WmUser save(@RequestBody WmUser wmUser) {
        wmUserService.save(wmUser);
        return wmUser; // 主要需要使用到wmUserId
    }
    @ApiOperation("按照名称查询用户")
    @GetMapping("/findByName/{name}")
    public WmUser findByName(@PathVariable("name") String name) {
        return wmUserService.getOne(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName,name));
    }

    @ApiOperation("按照id查询用户")
    @GetMapping("/findOne/{id}")
    public WmUser findWmUserById(@PathVariable("id") Integer id) {
        return wmUserService.getById(id);
    }
}