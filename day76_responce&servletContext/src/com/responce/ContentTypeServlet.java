package com.responce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ContentTypeServlet")
public class ContentTypeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    /*
     *  1. 如果服务器不响应这个头Content-Type:
     *       浏览器默认用本地编码 (中国大陆的中文简体版: GBK)解码
     *    所以我们可以这么解决:
     *        response.setCharacterEncoding("gbk"); gbk编码 (idea中默认utf-8编码)
     *           有限制的(不推荐)
     *
     *   2.如果服务器响应这个头 content-type
     *       服务器会通过这个头告诉浏览器: 应该如何解码我的响应体数据
     *
     *           response.setHeader(头,"value1;value2")
     *       如果我们不设置value2,tomcat会默认设置 iso-8859-1
     *       相当于告诉浏览器用ISO-8859-1解码, 乱码啦
     *
     *    推荐:   response.setHeader("content-type","text/html;charset=utf-8");
     *
     *
     *   3. MIME 类型介绍
     *       区分文件类型
     *
     *       windows系统(后缀名) :    txt             html         json               jpg    avi
     *       网络传输中(MIME类型) :   text/plain     text/html    application/json
     *
     *           mime格式: 大类型/小类型
     *
     *           服务器告诉浏览器 : 应该以什么格式解析响应体正文
     * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setCharacterEncoding("gbk");
//        response.setHeader("content-type","text/html"); //Content-Type: text/html;charset=ISO-8859-1
//        response.setHeader("content-type","text/html;charset=utf-8");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("<h1>响应体:直接中文会乱码</h1>");
    }

}