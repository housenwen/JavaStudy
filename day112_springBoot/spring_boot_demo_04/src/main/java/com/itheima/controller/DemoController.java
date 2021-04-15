package com.itheima.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

@RestController
public class DemoController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/demo")
    public String demo(){

        try {
            //光速
            System.out.println("************88"+dataSource.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "hello springBoot";
    }
}
