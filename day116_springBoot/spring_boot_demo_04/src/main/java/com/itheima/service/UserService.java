package com.itheima.service;

import com.itheima.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 查询所有
     * @return
     */
    public List<User> list();
}
