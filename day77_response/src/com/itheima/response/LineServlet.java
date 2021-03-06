package com.itheima.response;

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
         * 响应行：   协议  状态码
         *
         *   response.setStatus(status); 设置响应的状态码。我们基本不用去设置。
         *                                  因为tomcat自动根据代码执行是否有异常，是否找到资源，自动的
         *                                      配置404,405,5000，200等状态
         *
         */

        response.setStatus(5000);


    }
}