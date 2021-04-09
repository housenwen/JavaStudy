package com.itheima.travel.dao;

import com.itheima.travel.config.SpringConfig;
import com.itheima.travel.pojo.User;
import com.itheima.travel.pojo.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void countByExample() {
    }

    @Test
    public void deleteByExample() {
    }

    @Test
    public void deleteByPrimaryKey() {

        userMapper.deleteByPrimaryKey(1l);
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    /**
     * 根据条件查询
     *
     *
     *
     *  {
     *      key1:value1,
     *      key2:value2,
     *
     *  }
     */
    @Test
    public void selectByExample() {

        //组装查询的条件的
        UserExample userExample = new UserExample();

        //组装条件
        userExample.createCriteria()
                .andUsernameLike("zhang%")
                .andSexEqualTo("0");

        //排序
        userExample.setOrderByClause("id desc");


        List<User> users = userMapper.selectByExample(userExample);
        for(User u:users){
            System.out.println(u);
        }
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByExampleSelective() {
    }

    @Test
    public void updateByExample() {

        //修改姓张的用户的密码和真实姓名
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameLike("zhang%");

        User user = new User();
        user.setRealName("张三123");
        user.setPassword("444666");

        userMapper.updateByExample(user,userExample);
    }

    @Test
    public void updateByPrimaryKeySelective() {

        //修改姓张的用户的密码和真实姓名
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameLike("zhang%");

        User user = new User();
        user.setRealName("张三123");
        user.setPassword("444666");


        userMapper.updateByExampleSelective(user,userExample);
    }

    @Test
    public void updateByPrimaryKey() {
    }
}