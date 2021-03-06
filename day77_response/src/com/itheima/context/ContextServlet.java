package com.itheima.context;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/contextServlet")
public class ContextServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取servletContext对象
        ServletContext servletContext = getServletContext();

        /**
         *  servletContext对象存放着当前项目相关的信息
         *      1.获取项目发布时的虚拟项目名
         *      2.获取项目发布时的发布路径
         *      3.获取配置的初始化参数
         *      4.获取文件的mimeType类型
         *
         */
        //获取当前项目发布时的虚拟项目名
        String contextPath = servletContext.getContextPath();
        System.out.println("虚拟项目名:"+contextPath);

        //获取项目发布时的发布路径
        String realPath = servletContext.getRealPath("");
        System.out.println("项目发布时的发布路径:"+realPath);
        //获取项目发布时具体资源的路径  ： 项目路径+资源路径
        String realPath1 = servletContext.getRealPath("img/1.png");
        System.out.println("1.png的发布路径:"+realPath1);

        //获取项目级别的初始化参数
        String contextLocation = servletContext.getInitParameter("contextLocation");
        System.out.println("spring配置文件的名字:"+contextLocation);

        //获取文件的mime类型， mime类型就是我们后缀的机器识别具体名称  例如  .txt   text/plain
        String mimeType = servletContext.getMimeType("a.txt");
        System.out.println("a.txt的mimeType是："+mimeType);
    }
}