package com.itheima.service;

import com.itheima.config.SpringConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserServiceTest {


    @Autowired
    private UserService userService;

    @Test
    public void login() {

        userService.login("a");
    }

    @Test
    public void register() {

        userService.register();
    }
}