package com.itheima.controller;

import com.itheima.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/json")
public class Controller_02_json {

    @RequestMapping("/returnJson")
    public User returnJson(){
        User user = new User();
        user.setAge(18);
        user.setUsername("zhangsan");
        user.setSex("male");
        return user;
    }


    @RequestMapping("/returnJson2")
    public List<User> returnJson2(){
        User user = new User();
        user.setAge(18);
        user.setUsername("zhangsan");
        user.setSex("male");

        User user2 = new User();
        user2.setAge(12);
        user2.setUsername("zhangsan2");
        user2.setSex("male2");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);

        return userList;
    }
}
