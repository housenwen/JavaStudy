package com.itheima.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/redirectServlet")
public class RedirectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("redirect处理了请求");

        //todo 重定向
      /*  todo 1.设置302状态码
              response.setStatus(302);
              2.设置location响应头
            response.setHeader("location","error.html");
            response.setHeader("location","http://www.baidu.com");
            */


        //重定向封装后的api
//        response.sendRedirect("error.html");
        response.sendRedirect("http://www.baidu.com");
    }
}