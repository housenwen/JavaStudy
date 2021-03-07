package com.cookie;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/SetCookie")
public class SetCookie extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("用户添加小米手机到购物车");
        //1. 创建一个Cookie对象
        Cookie cookie = new Cookie("phone","xiaomi");
        //2. 将cookie响应给浏览器
        response.addCookie(cookie);
    }

}