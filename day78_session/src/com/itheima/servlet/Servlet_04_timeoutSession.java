package com.itheima.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet_04_timeoutSession")
public class Servlet_04_timeoutSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        /*
                让浏览器关闭再开启的情况下，保存session唯一标识的cookie依然存在。--设置cookie的生存时间

         */
        String id = request.getSession().getId();

        System.out.println(id);
        Cookie cookie = new Cookie("JSESSIONID",id);

        cookie.setMaxAge(60*60);

        response.addCookie(cookie);

    }
}