package com.tomcat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/successServlet")
public class SuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取登录成功的用户名
        String username = (String) request.getAttribute("username");

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        //响应一个成功页面
        writer.write("<html>");
        writer.write("<head>");
        writer.write("<title>login success</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<font>欢迎回来，"+username+"</font>");
        writer.write("</body>");
        writer.write("</html>");
    }
}

