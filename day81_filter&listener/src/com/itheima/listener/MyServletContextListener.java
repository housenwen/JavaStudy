package com.itheima.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/*
 todo   用来监听servletContext对象的创建和销毁的。
    servletContext:tomcat启动，创建
                   tomcat关闭，销毁
 */
public class MyServletContextListener implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("当前监听到servletContext对象创建的时候，执行");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("当监听到servletContext对象销毁的时候，执行");
    }
}