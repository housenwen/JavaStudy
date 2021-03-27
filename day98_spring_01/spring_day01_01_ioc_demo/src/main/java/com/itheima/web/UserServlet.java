package com.itheima.web;

import com.itheima.service.UserService;
import com.itheima.utils.BeansFactory;

public class UserServlet {

    public static void main(String[] args) {

        //通过工厂获取对象
        UserService userService = (UserService) BeansFactory.getBean("userService");

        //调用service
        userService.login();
    }
}
