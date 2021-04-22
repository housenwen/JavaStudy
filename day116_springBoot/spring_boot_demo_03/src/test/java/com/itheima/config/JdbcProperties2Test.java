package com.itheima.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcProperties2Test {

    @Autowired
    private JdbcProperties2 jdbcProperties2;


    @Test
    public void demo(){
        System.out.println(jdbcProperties2);
    }
}