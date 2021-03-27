package com.itheima.service.impl;

import com.itheima.service.UserService;

public class UserServiceImpl2 implements UserService {





    @Override
    public void login() {
        System.out.println("登录功能");
    }

    public void init(){
        System.out.println("userService2创建了,调用init方法");
    }

    public void destroy(){
        System.out.println("userService2销毁了,调用destroy方法");
    }
}
