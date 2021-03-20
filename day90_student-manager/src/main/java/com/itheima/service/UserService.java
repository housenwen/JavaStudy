package com.itheima.service;

import com.itheima.bean.User;
import com.itheima.dao.UserMapper;
import com.itheima.util.SqlSessionUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserService {

    public List<User> login(User user) {
        SqlSession sqlSession = SqlSessionUtil.getSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.login(user);
        sqlSession.close();
        return list;
    }
}
