package com.itheima.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/servlet_01_createSession")
public class Servlet_01_createSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *
         *
         *  request.getSession();
         *          获取session对象。
         *         本质：优先通过cookie中保存的session的id寻找session的。
         *              只要没找到，直接创建一个新的。
         *
         */
        String remoteAddr = request.getRemoteAddr();


        HttpSession session = request.getSession();
        System.out.println("远程ip:"+remoteAddr+"     session的唯一标识:"+session.getId());
    }
}