package com.itheima.web;

import com.itheima.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LifecycleDemo {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        UserService userService2 = (UserService) applicationContext.getBean("userService2");
        Object userService3 = applicationContext.getBean("userService2");

        applicationContext.close();

        //applicationContext.getBean("userService2");

        userService2.login();

    }
}
