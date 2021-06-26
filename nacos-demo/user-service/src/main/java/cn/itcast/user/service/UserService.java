package cn.itcast.user.service;

import cn.itcast.user.entity.User;
import cn.itcast.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryById(Long id){
        return userMapper.findById(id);
    }
}
