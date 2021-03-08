package com.service;

import com.dao.UserMapper;
import com.pojo.User;
import com.utils.SessionUtil;

public class UserService {
    public User login(String username, String password) {

        //调用dao方法查询数据库
        UserMapper userMapper = (UserMapper) SessionUtil.getMapper(UserMapper.class);
        return userMapper.findUserByUsernameAndPassword(username,password);
    }
}
