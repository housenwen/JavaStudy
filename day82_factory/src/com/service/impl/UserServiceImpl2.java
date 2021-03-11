package com.service.impl;

import com.service.UserService;

public class UserServiceImpl2 implements UserService {
    @Override
    public void login() {
        System.out.println("更改后的登录功能");
    }
}
