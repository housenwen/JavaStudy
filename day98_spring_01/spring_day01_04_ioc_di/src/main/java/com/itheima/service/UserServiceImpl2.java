package com.itheima.service;

import com.itheima.dao.UserDaoImpl;

public class UserServiceImpl2 {

    private String username;
    private int age;
    private String game;

    private UserDaoImpl userDao;


    public void setAaa(String aaa){
        System.out.println("setAaa方法调用了，但是没有aaa属性");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public void login(){
       userDao.findAll();
    }



}
