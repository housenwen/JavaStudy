package com.utils;

import java.util.ResourceBundle;

public class BeansFactory {
    /**
     * 工厂模式：专门用来创建对象的
     * @param beanName
     * @return
     */

    public static Object getBean(String beanName){

        ResourceBundle rb = ResourceBundle.getBundle("beans");
        String classPath = rb.getString(beanName);

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
