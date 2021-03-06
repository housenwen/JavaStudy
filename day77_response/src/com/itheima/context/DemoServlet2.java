package com.itheima.context;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/demoServlet2")
public class DemoServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取servletContext对象
        ServletContext servletContext = getServletContext();
        System.out.println("servletContext对象的地址的hash值："+servletContext);

        //获取域中的数据
        Object msg = servletContext.getAttribute("msg");
        System.out.println("servletContext域中的数据:"+msg);

    }
}