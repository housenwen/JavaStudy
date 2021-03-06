package com.responce;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/bodyServlet")
public class BodyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /*
    * 响应体中包含 响应数据的正文
    响应是服务器给浏览器发送数据: 输出流
    * API（输出流对象）
        1. 字符输出流 : 用于向浏览器输出字符数据(文本)
                PrintWriter getWriter()
        2. 字节输出流 : 用于向浏览器输出二进制数据(比如文件下载)
                ServletOutputStream getOutputStream()

        注意:
            1. 如果输出字符, 一般都是直接显示在网页上的
            2. 如果输出字节流,有可能显示在网页,也有可能下载
    * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //打印流 (字符输出流)
//        PrintWriter writer = response.getWriter();
//        writer.println("hello world");

//        response.getWriter().println("hello world");

        // 字节输出流
        ServletOutputStream os = response.getOutputStream();
        os.write(97);

    }
}
