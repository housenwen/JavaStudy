package com.itheima.web;

import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {

    //@Autowired //注入不了，因为servlet是tomcat创建。
    private UserService userService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*
            问题：每个servlet中都这么写的话，spring容器会被反复创建多次。浪费资源！！！

               如何解决：
                    1.spring容器只需要创建一次。
                    2.tomcat初始化的时候创建。
                         一：  servlet的init+<load-on-startup>
                         二：  servletContextListener:监听servletContext对象的创建和销毁的。


                    3.spring容器创建好了之后，所有的servlet都能够拿到这个spring容器。
                            servletContext域



         */



        //创建spring容器，从容器中获取对象
        //ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        //ApplicationContext ac = (ApplicationContext) getServletContext().getAttribute("ac");
        ApplicationContext ac = (ApplicationContext) getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);



        userService = (UserService)ac.getBean("userService");


        userService.findAll();

    }
}