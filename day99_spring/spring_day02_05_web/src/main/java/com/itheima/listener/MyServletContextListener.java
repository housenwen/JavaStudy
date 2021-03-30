package com.itheima.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    /**
        监听servletContext对象的创建，
        所以servletContext对象随着项目的启动而创建
        这个方法随着tomcat的启动而自动执行
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("spring容器初始化start**************");
        //获取被监听的对象
        ServletContext servletContext = servletContextEvent.getServletContext();

        //获取全局配置参数
        String contextLocation = servletContext.getInitParameter("contextLocation");


        //1.创建spring容器
        ApplicationContext ac = new ClassPathXmlApplicationContext(contextLocation);


        //2.保存到servletContext域中
        servletContext.setAttribute("ac",ac);
        System.out.println("spring容器初始化end**************");

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
