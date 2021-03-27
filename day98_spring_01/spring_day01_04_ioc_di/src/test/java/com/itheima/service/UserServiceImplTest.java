package com.itheima.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    @Test
    public void login() {


        //创建spring工厂
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        UserServiceImpl2 userService = (UserServiceImpl2) ac.getBean("userService4");
//        UserServiceImpl userService3= (UserServiceImpl) ac.getBean("userService3");

        UserServiceImpl3 userServiceImpl3 = (UserServiceImpl3)ac.getBean("userService5");
        //调用service方法
        //userService.login();



        UserServiceImpl3 userServiceImpl31 = new UserServiceImpl3();


        System.out.println(userServiceImpl3);
    }
}