package com.itheima.service.impl;

import com.itheima.dao.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class UserServiceImpl implements UserService {
    @Override
    public boolean validateUsername(String username) {

        //调用dao层方法查询
        SqlSession session = SqlSessionUtil.getSession();
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.findUserByUsername(username);
        session.close();


        return user==null;
    }
}
