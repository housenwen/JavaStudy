package com.itheima.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService2;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl2 extends ServiceImpl<UserMapper,User> implements UserService2 {
}
