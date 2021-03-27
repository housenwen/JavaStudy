package com.itheima.service.impl;

import com.itheima.service.UserService;

public class UserServiceImpl implements UserService {

    public UserServiceImpl() {
        System.out.println("userService创建了****");
    }

    @Override
    public void login() {
        System.out.println("登录功能");
    }
}
