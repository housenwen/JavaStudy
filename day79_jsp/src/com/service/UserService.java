package com.service;

import com.mapper.UserMapper;
import com.pojo.User;
import com.utils.MapperUtils;
import com.utils.SessionUtil;


import java.util.List;

public class UserService {
    public List<User> findAllUser(){
        UserMapper mapper = (UserMapper) MapperUtils.getMapper(UserMapper.class);
        UserMapper mapper1 = SessionUtil.getMapper(UserMapper.class);

//        return mapper.findAllUser();
        return mapper1.findAllUser();
    }
}
