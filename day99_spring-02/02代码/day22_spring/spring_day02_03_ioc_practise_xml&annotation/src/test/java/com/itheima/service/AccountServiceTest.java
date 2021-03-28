package com.itheima.service;

import com.itheima.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

//    @Before
//    public void setUp() throws Exception {
//        //创建spring容器，获取对象
//        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
//        accountService = (AccountService)ac.getBean("accountServiceImpl");
//
//
//        AccountService  accountService2 = (AccountService)ac.getBean("accountServiceImpl");
//        AccountService  accountService3 = (AccountService)ac.getBean("accountServiceImpl");
//        System.out.println(accountService2);
//        System.out.println(accountService3);
//
//
//        ac.close();
//    }

    @Test
    public void findAll() {
        List<Account> all = accountService.findAll();
        all.forEach(account -> {
            System.out.println(account);
        });
    }



}