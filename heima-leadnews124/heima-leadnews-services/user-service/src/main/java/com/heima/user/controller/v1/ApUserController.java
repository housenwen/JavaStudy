package com.heima.user.controller.v1;
import com.heima.model.user.pojo.ApUser;
import com.heima.user.service.ApUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Api(value = "app用户管理API",tags = "app用户管理API")
@RestController
@RequestMapping("/api/v1/user")
public class ApUserController{
    @Autowired
    private ApUserService apUserService;
    @ApiOperation("根据id获取apUser信息")
    @GetMapping("/{id}")
    public ApUser findUserById(@PathVariable("id") Integer id) {
        return apUserService.getById(id);
    }
}