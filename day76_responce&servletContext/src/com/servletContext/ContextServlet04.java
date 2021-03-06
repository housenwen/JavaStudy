package com.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *   参数放在配置文件, 然后在代码中获取配置文件中的参数
 *       (解耦)
 * */
@WebServlet("/ContextServlet04")
public class ContextServlet04 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取ServletContext对象
        ServletContext context = request.getServletContext();
        //获取web.xml中配置的全局参数
        String encode = context.getInitParameter("encode");
        System.out.println(encode);//gbk
    }

}