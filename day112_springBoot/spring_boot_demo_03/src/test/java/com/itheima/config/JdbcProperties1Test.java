package com.itheima.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdbcProperties1Test {


    @Autowired
    private JdbcProperties1 jdbcProperties1;

    @Test
    public void demo(){
        System.out.println(jdbcProperties1);
    }

}