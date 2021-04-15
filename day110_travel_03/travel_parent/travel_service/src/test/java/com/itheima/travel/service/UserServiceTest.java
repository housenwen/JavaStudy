package com.itheima.travel.service;

import com.itheima.travel.config.SpringConfig;
import com.itheima.travel.req.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static org.junit.Assert.*;


public class UserServiceTest extends BasicTest {



    @Test
    public void registerUser() {

        //注册
        System.out.println("----------注册----------");
        UserVo userVo = UserVo.builder()
                .username("liudehua333")
                .password("123456")
                .birthday(new Date())
                .email("liudehua@qq.com")
                .realName("刘德华")
                .sex("1")
                .build();

        userService.registerUser(userVo);

        //判断是否登录
//        boolean flag = userService.isLogin();
//        System.out.println("------是否登录："+flag+"-----");
//
//        //退出
//        System.out.println("------退出-----");
//        userService.logoutUser();
//
//
//        boolean flag2 = userService.isLogin();
//        System.out.println("------是否登录："+flag2+"-----");
//
//
//        System.out.println("--------手动登录----------");
//        UserVo liudehua = UserVo.builder()
//                .username("liudehua")
//                .password("123456")
//                .build();
//        userService.loginUser(liudehua);
//
//
//        boolean flag3 = userService.isLogin();
//        System.out.println("------是否登录："+flag3+"-----");

    }

    @Test
    public void loginUser() {
    }

    @Test
    public void logoutUser() {
    }

    @Test
    public void isLogin() {
    }
}