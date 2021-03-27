package com.itheima.service.impl;

import com.itheima.service.UserService;

public class UserServiceImpl2 implements UserService {
    @Override
    public void login() {
        System.out.println("这是登录功能 ****** 改写");
    }
}
