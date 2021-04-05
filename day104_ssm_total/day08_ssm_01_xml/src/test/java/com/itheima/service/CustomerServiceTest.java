package com.itheima.service;

import com.itheima.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CustomerServiceTest {


    @Autowired
    private CustomerService customerService;

    @Test
    public void findAll() {

        List<Customer> all = customerService.findAll();
        for(Customer c:all){
            System.out.println(c);
        }
    }
}