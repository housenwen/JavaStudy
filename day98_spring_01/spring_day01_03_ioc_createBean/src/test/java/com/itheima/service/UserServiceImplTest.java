package com.itheima.service;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class UserServiceImplTest {



    @Test
    public void createBean(){

        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        Object userService = ac.getBean("userService2");
        System.out.println(userService);

    }
}