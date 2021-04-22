package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    // 消费者根据接口生成接口的代理实现类
    // 在代理实现类中完成了远程过程调用
    @Reference
    private UserService service;

    @RequestMapping("/findAll")
    public List<User> findAll(){
        System.out.println("消费者执行了...");
        return service.findAll();
    }
}
