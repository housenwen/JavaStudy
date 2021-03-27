package com.itheima.service;

import com.itheima.dao.UserDaoImpl;

public class UserServiceImpl {

    private String username;
    private int age;
    private String game;

    private UserDaoImpl userDao;


    public UserServiceImpl(String username, int age, String game, UserDaoImpl userDao) {
        this.username = username;
        this.age = age;
        this.game = game;
        this.userDao = userDao;
    }

    public void login(){
       userDao.findAll();
    }



}
