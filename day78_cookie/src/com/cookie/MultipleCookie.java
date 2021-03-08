package com.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MultipleCookie")
public class MultipleCookie extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("用户添加多个cookie");
        // 1. 创建多个cookie对象
        Cookie cookie1 = new Cookie("name","lucy");
        Cookie cookie2 = new Cookie("age","18");
        // 2. 通过response响应多个
        response.addCookie(cookie1);
        response.addCookie(cookie2);
    }

}

