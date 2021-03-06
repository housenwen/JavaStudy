package com.itheima.response;

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
         * response.setHeader(name,value); 给指定的响应头设置指定的值
         *
         *      常用响应头：
         *          refresh:定时刷新，值的格式   seconds,url
         *          content-type: 响应体的解析方式
         *                          一般情况下，给出的默认值是 text/html(mime类型) html解析方式
         *          location:配置302实现重定向
         *
         */
        //自定义响应头
        //response.setHeader("aaa","bbb");

        //定时刷新
        //response.setHeader("refresh","10,http://www.baidu.com");

        //响应体的解析格式,普通文本
        //response.setHeader("content-type","text/plain");
        //设置不存在的解析格式
        //response.setHeader("content-type","text/plsssssain");


        //解决响应的乱码问题,通知浏览器，以html方式解析响应体内容，并且采用utf-8编码
        response.setHeader("content-type","text/html;charset=utf-8");

        //响应体
        response.getWriter().write("<font color='red'>你好</font>");

    }
}