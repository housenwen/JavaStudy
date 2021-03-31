package com.itheima.service.impl;

import com.itheima.service.UserService;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Override
    public String login(String a) {
        System.out.println("登录功能");
        //int i = 1/0;
        return "abc";
    }

    @Override
    public void register() {
        System.out.println("注册功能");
    }
}
