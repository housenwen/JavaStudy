package com.itheima.service.impl;

import com.itheima.service.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        System.out.println("sayHello服务执行...");
        return "Hello "+name+" !!! ";
    }
}
