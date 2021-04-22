package com.itheima.service.impl;

import com.itheima.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("生产者执行了...");
        return "Hello "+name;
    }
}
