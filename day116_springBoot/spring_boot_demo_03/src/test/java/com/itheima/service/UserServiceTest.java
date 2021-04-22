package com.itheima.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)  //替换单元测试运行器
@SpringBootTest  //声明这是springBoot的单元测试
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void demo(){
        System.out.println(userService);
    }
}