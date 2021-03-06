package com.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AppInfoServlet")
public class AppInfoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*
         *   1. 获取全局配置参数 : 解耦
         *       变量和变量值解耦: 如果都写在一起,要改就要改源代码 -> 编译,部署
         *       解耦之后,只需要改配置文件
         * */
        ServletContext context = getServletContext();
        String encode = context.getInitParameter("encode");
        System.out.println(encode);

        /*
         * 2. 获取项目的真实路径
         *   E:\mywork\class107\out\artifacts\day03_response_war_exploded\resource\1.zip
         *
         *   api: 相对于  out\artifacts\day03_response_war_exploded 而言的
         *      对应项目源码路径 当前项目/web
         * */
        String realPath = context.getRealPath("/resource/1.zip"); // 相对路径写法 (兼容不同环境)
//        String realPath = "E:\\mywork\\class107\\out\\artifacts\\day03_response_war_exploded\\resource\\1.zip"; // 绝对路径写法
        System.out.println(realPath);

        /*
         *  3.获取资源的mime类型
         * */
        String mimeType = context.getMimeType("3.html");
        System.out.println("mimeType:" + mimeType);

    }

}

