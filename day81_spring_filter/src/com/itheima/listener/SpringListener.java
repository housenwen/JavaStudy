package com.itheima.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class SpringListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("**************spring容器初始化start**************");

        //获取被监听的对象，servletContext
        ServletContext servletContext = sce.getServletContext();

        //1.读取web.xml中的配置文件
        String location = servletContext.getInitParameter("location");

        System.out.println("根据spring的配置文件："+location+"   创建spring容器");

        //2.存放到servletContext域中。
        System.out.println("将spring容器保存到servletContext域中");
        servletContext.setAttribute("ac","spring容器");

        System.out.println("**************spring容器初始化end**************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}

