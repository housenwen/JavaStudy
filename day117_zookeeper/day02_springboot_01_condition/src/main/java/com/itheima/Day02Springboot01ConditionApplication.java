package com.itheima;

import com.itheima.pojo.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Day02Springboot01ConditionApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(Day02Springboot01ConditionApplication.class, args);
        // 获取IOC容器中的user对象
        // User user = ac.getBean("user", User.class);
        // System.out.println(user);

        User user = ac.getBean("user1", User.class);
        System.out.println(user);
    }

}
