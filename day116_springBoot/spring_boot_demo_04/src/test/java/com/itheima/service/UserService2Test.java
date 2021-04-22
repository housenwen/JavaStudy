package com.itheima.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserService2Test {

    @Autowired
    private UserService2 userService2;

    @Test
    public void list(){
        userService2.list().forEach(user -> {
            System.out.println(user);
        });
    }
}