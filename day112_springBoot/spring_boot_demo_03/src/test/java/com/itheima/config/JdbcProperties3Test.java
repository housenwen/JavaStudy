package com.itheima.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@EnableConfigurationProperties(JdbcProperties3.class)  //此处需要使用JdbcProperties3类型的对象
public class JdbcProperties3Test {


    @Autowired
    private JdbcProperties3 jdbcProperties3;

    @Test
    public void demo(){
//        System.out.println(jdbcProperties3.getMap().get("key1"));
//        System.out.println(jdbcProperties3.getMap().get("key2"));

       // System.out.println(jdbcProperties3.getDog());
        jdbcProperties3.getGirls().forEach(girl->{
            System.out.println(girl);
        });
    }

}