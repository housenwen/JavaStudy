package com.itheima.test7.test;

import com.itheima.test7.mapper.UserMapper;
import com.itheima.test7.mapper.UserRoleMapper;
import com.itheima.test7.pojo.User;
import com.itheima.test7.utils.SessionUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TestAll {
    @Test
    public void test1(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        User user = mapper.selectByPrimaryKey(1);
        System.out.println(user);
        SessionUtil.close();
    }

    @Test
    public void test2(){
        try {
            UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
            //1.插入用户信息，并返回主键id
            User user = new User();
            user.setUserName("王八");
            int count = mapper.insert(user);
            System.out.println(count);
            //2.向中间表批量插入数据
            List<Integer> roleIds = Arrays.asList(1, 6);
            UserRoleMapper userRoleMapper = SessionUtil.getMapper(UserRoleMapper.class);
            int count2 = userRoleMapper.batchInsert(user.getUserId(), roleIds);
            System.out.println(count2);
            //3.事务提交
            SessionUtil.commit();
        } catch (Exception e) {
            e.printStackTrace();
            //事务回滚
            SessionUtil.rollback();
        }finally {
            //4.关闭资源
            SessionUtil.close();
        }


    }

    @Test
    public void test3(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        List<User> users = mapper.findUsersByRoleName("超级管理员");
        System.out.println(users);
        SessionUtil.close();
    }

    @Test
    public void test4(){
        UserMapper mapper = SessionUtil.getMapper(UserMapper.class);
        User user = mapper.findUserInfoByUserName("张三");
        System.out.println(user);
        SessionUtil.close();
    }
}
