package com.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MaxAgeCookie")
public class MaxAgeCookie extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.创建cookie对象
        Cookie cookie = new Cookie("product", "xiaomi10");
        // 2.设置cookie存活时间
        cookie.setMaxAge(30);// 存活30秒，到期自动销毁

        //3. response响应cookie
        response.addCookie(cookie);
    }

}
