package com.itheima.web;

import com.itheima.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class UserServlet {

    public static void main(String[] args) {

        //创建spring工厂对象
        //spring中的所有路径：全部建议加上classpath:
        System.out.println("*************工厂初始化start*******************");
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

//        Resource resource = new ClassPathResource("applicationContext.xml");
//        BeanFactory ac = new XmlBeanFactory(resource);

        System.out.println("*************工厂初始化end*******************");

        Object userService2 = ac.getBean("userService2");
        System.out.println(userService2);
//        Object userService3 = ac.getBean("userService2");
//        System.out.println(userService3);


        ac.close();

        //获取对象
//        UserService userService = (UserService) ac.getBean("userService");
//        userService.login();

    }
}
