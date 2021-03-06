package com.responce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/refreshServlet")
public class RefreshServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
      /**
    * * API
        1. 设置指定头名称和对应的值
                void setHeader(String name, String value)
        2. value值可以由多个参数组成，不同参数之间使用分号隔开：
                response.setHeader(key,"value1;value2")；
        3. 常用响应头：
                refresh：定时刷新；

                response.setHeader("refresh","time;url");

                服务器通知浏览器: 你过time秒之后去访问url
    * */
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          response.setHeader("refresh","5;http://www.baidu.com");

          response.setContentType("text/html;charset=utf-8");
          response.getWriter().print("付款成功~~5秒后跳转到订单页面");
      }

}
