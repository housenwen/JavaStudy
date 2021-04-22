package com.itheima.mock;

import com.itheima.service.HelloService;

/**
 * 降级的类
 */
public class HelloServiceMoke implements HelloService {
    @Override
    public String sayHello(String name) {
        return "客官,服务器忙,请稍后访问....";
    }
}
