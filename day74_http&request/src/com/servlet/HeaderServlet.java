package com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/headerServlet")
public class HeaderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *  request.getHeader(headerName);  获取指定请求头的信息
         */

        String remoteAddr = request.getRemoteAddr();
        if(remoteAddr.equals("fe80:0:0:0:30df:dbb3:47e8:a51a%24")){
            System.out.println("客户端："+remoteAddr+"请求已经被拦截");
            response.getWriter().write("do not saorao wo");
            return;
        }

        String userAgent = request.getHeader("user-agent");
        if(userAgent.indexOf("Firefox")!=-1){
            System.out.println("当前是火狐浏览器");
        }else if(userAgent.indexOf("Chrome")!=-1){
            System.out.println("当前是谷歌浏览器访问的");
        }
    }
}
