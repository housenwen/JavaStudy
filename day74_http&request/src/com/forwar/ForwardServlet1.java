package com.forwar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ForwardServlet1")
public class ForwardServlet1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ForwardServlet1执行了，校验了用户名和密码的正确性");


        /**
         *  request域：临时的作为一个存放数据的对象。
         *          域：表示范围，request域的作用范围：一次请求，一次响应。
         *
         */

        //向request域中存放数据
        request.setAttribute("msg","消息");


        //转发到另外一个现有的servlet来给出成功的响应
        //获取请求的转发器
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("ForwardServlet2");
        //进行请求的转发
        requestDispatcher.forward(request,response);
    }
}
