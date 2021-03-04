package com.tomcat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {


    private String dbUsername = "zhangsan";
    private String dbPassword = "123456";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取请求中的用户名和密码
        //用户名
        String username = request.getParameter("username");
        //密码
        String password = request.getParameter("password");
        //2.和正确的用户名和密码进行比较
        if(dbUsername.equals(username)&&dbPassword.equals(password)){
            //登录成功，给出登录成功的响应
            // request.getRequestDispatcher("success.html").forward(request,response);
            //传递登录成功的用户名
            request.setAttribute("username",username);

            request.getRequestDispatcher("successServlet").forward(request,response);
        }else{
            //登录失败，给出登录失败的响应
            request.getRequestDispatcher("error.html").forward(request,response);
        }
    }
}