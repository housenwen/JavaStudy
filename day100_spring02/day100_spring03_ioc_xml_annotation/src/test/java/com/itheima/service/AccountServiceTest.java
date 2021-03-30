package com.itheima.service;

import com.itheima.pojo.Account;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class AccountServiceTest {

    private AccountService accountService;

    @Before
    public void setUp() throws Exception {
        //从spring容器获取service对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        accountService = (AccountService) ac.getBean("accountService");
    }

    @Test
    public void findAll() {
        List<Account> all = accountService.findAll();
        for(Account account:all){
            System.out.println(account);
        }
    }
}

