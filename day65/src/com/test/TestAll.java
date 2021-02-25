package com.test;

import com.mapper.UserMapper;
import com.pojo.User;
import com.utils.SessionUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestAll {

    @Test
    public void test1(){
        UserMapper userMapper = SessionUtil.getMapper(UserMapper.class);
        List<User> users = userMapper.findAll();
        System.out.println(users);
        SessionUtil.close();
    }

    /**
     * 添加用户并批量添加角色
     */
    @Test
    public void test2(){
        //1.添加用户,获取主键回填值
        User user = new User();
        user.setUserName("王五");
        UserMapper userMapper = SessionUtil.getMapper(UserMapper.class);
        userMapper.addUser(user);
        System.out.println(user.getUserId());
        //2.给新添加用户批量添加角色
        List<Integer> roleIds = Arrays.asList(1, 6);
        userMapper.addRole4User(user.getUserId(),roleIds);
        SessionUtil.commit();
        SessionUtil.close();
    }

    /**
     * 根据角色名称查询用户集合
     */
    @Test
    public void test3(){
        UserMapper userMapper = SessionUtil.getMapper(UserMapper.class);
        List<User> users = userMapper.findUsersByRole("超级管理员");
        System.out.println(users);
        SessionUtil.close();
    }

    /**
     * 根据用户名称查询用户信息,包含角色集合和权限集合
     */
    @Test
    public void test4(){
        UserMapper userMapper = SessionUtil.getMapper(UserMapper.class);
        User user = userMapper.findUserInfo("张三");
        System.out.println(user);
        SessionUtil.close();
    }
}


