package com.itheima.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SingleBeansFactory2 {

    private static   ResourceBundle rb = null;
    private static  Map<String,Object> beansFactory = new HashMap<>();

    static {
        //拿到配置文件
        rb = ResourceBundle.getBundle("beans");
        //读取配置中所有的参数，并且创建对象，放到map中即可
        Enumeration<String> keys = rb.getKeys();
        while (keys.hasMoreElements()){
            String beanName = keys.nextElement();
            String classPath = rb.getString(beanName);
            //创建对象
            Object obj = null;
            try {
                obj = Class.forName(classPath).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            //放到map中
            beansFactory.put(beanName,obj);
        }
        System.out.println("**********ioc工厂初始化完毕**********");
    }


    /*
            对象创建的时机：项目初始化，对象全部创建完毕

     */


    public static Object getBean(String beanName){

        //直接从map中获取即可
        Object obj = beansFactory.get(beanName);

        return obj;
    }


    public static void main(String[] args) {
        Object userService = getBean("userService");
        Object userService2 = getBean("userService");
        System.out.println(userService);
        System.out.println(userService2);
    }
}
