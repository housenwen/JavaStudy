package com.cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 创建同name+path的cookie,value写啥都无所谓
        Cookie cookie = new Cookie("name", "无所谓");
        cookie.setPath("/day76_cookie");//默认也是项目虚拟路径,可以不写
        //2. 设置存活时间为0
        cookie.setMaxAge(0);
        //3. 发送此cookie到浏览器
        response.addCookie(cookie);
    }

}
