package com.itheima.utils;

import com.itheima.service.UserServiceImpl;

public class BeansFactoryUtils {


    public static UserServiceImpl getUserService(){

        System.out.println("工厂类的静态方法创建对象");
        return new UserServiceImpl();
    }


    public static Object getMapper(){
        //return session.getMapper();
        return null;
    }
}
