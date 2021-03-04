package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/lineServlet")
public class LineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *  请求行：   请求的方式  请求的地址   请求的协议
         *      getMethod(); 获取请求的方式
         *      getRequestURI();获取请求的地址
         *      getProtocol();获取请求的协议
         *      getRemoteAddr(); 获取请求的客户端所在机器的ip地址
         */

        String method = request.getMethod();
        System.out.println("请求的方式:"+method);

        String requestURI = request.getRequestURI();
        System.out.println("请求的地址"+requestURI);


        String protocol = request.getProtocol();
        System.out.println("请求的协议："+protocol);


        String remoteAddr = request.getRemoteAddr();
        System.out.println("获取请求的远程客户机的ip地址:"+remoteAddr);
    }
}