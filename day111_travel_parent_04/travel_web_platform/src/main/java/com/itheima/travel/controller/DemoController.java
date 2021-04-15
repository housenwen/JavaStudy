package com.itheima.travel.controller;

import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
@CrossOrigin
public class DemoController {


    @Autowired
    private RedissonClient redissonClient;

    @RequestMapping("/demo")
    @ResponseBody //json字符串
    public String demo(){
        System.out.println(redissonClient);
        return "hello travel project";
    }
}
