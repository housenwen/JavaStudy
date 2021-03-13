package com.itheima.service;

import com.itheima.dao.UserMapper;
import com.itheima.pojo.User;
import com.itheima.utils.SessionUtil;

public class UserService {
    public User login(String username, String password) {

        //调用dao方法查询数据库
        UserMapper userMapper =  SessionUtil.getMapper(UserMapper.class);
        return userMapper.findUserByUsernameAndPassword(username,password);
    }
}
