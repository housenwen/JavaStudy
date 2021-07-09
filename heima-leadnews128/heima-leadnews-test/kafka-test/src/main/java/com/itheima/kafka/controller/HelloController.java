package com.itheima.kafka.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/9 11:39
 **/
@RestController
public class HelloController {
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;
    @GetMapping("hello")
    public String hello(String content){
        Map map = new HashMap<>();
        map.put("name","小明");
        map.put("age","18");
        kafkaTemplate.send("heima-002","message-key", JSON.toJSONString(map));
        return "ok";
    }
}
