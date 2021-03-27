package com.itheima.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SingleBeansFactory {

    private static   ResourceBundle rb = null;
    private static  Map<String,Object> beansFactory = new HashMap<>();

    static {
        rb = ResourceBundle.getBundle("beans");
    }


    /*
            对象创建的时机：使用时创建对象
            单例工厂的改造：
            1.声明map集合用来保存工厂创建的对象.
            2.getBean方法，优先从map中获取对象
                如果有，直接返回。
                如果没有，创建对象，放到map中，然后再返回。
     */


    public static Object getBean(String beanName){

        //优先从map中获取
        Object obj = beansFactory.get(beanName);
        if(obj==null){
            //读取配置
            String classPath = rb.getString(beanName);
            //反射机制创建对象

            try {
                obj = Class.forName(classPath).newInstance();
                //保存到map中
                beansFactory.put(beanName,obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }


    public static void main(String[] args) {
        Object userService = getBean("userService");
        Object userService2 = getBean("userService");
        System.out.println(userService);
        System.out.println(userService2);
    }
}
