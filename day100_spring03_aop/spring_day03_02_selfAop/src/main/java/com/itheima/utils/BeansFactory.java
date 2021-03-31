package com.itheima.utils;

import java.util.ResourceBundle;

public class BeansFactory {

    private static   ResourceBundle rb = null;

    static {
        rb = ResourceBundle.getBundle("beans");
    }

    public static Object getBean(String beanName){
        //读取配置
        String classPath = rb.getString(beanName);
        //反射机制创建对象
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
        return obj;
    }
}
