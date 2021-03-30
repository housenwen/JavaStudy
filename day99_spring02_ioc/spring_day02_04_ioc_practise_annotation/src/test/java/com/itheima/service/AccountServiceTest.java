package com.itheima.service;

import com.itheima.config.SpringConfig;
import com.itheima.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) //使用spring 的运行器，替代junit自带的运行器
@ContextConfiguration(classes = SpringConfig.class)    //配置类或者配置文件的位置
public class AccountServiceTest {

    @Autowired //自动注入
    private AccountService accountService;



    @Test
    public void findAll() {
        List<Account> all = accountService.findAll();
        all.forEach(account -> {
            System.out.println(account);
        });
    }
}