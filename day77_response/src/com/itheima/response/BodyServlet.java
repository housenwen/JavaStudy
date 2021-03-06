package com.itheima.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/bodyServlet")
public class BodyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *  response.getWriter(); 获取字符流响应体对象
         */
        //获取字符流响应体
        //通过响应头，解决响应的乱码问题
        //response.setHeader("content-type","text/html;charset=utf-8");
        //响应乱码的简写方式
        response.setContentType("text/html;charset=utf-8");

        PrintWriter writer = response.getWriter();



        writer.write("<html>");
        writer.write("<head><title>this is title</title></head>");
        writer.write("<body>");
        writer.write("<font color='red'>成功了</font>");
        writer.write("</body>");
        writer.write("</html>");
    }
}