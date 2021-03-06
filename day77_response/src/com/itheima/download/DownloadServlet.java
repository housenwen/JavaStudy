package com.itheima.download;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.security.Principal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

@WebServlet(urlPatterns = "/downloadServlet")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         *      响应行： 响应的协议，响应的状态码
         *      响应头： http协议规范的
         *      响应体： 正文内容
         */

        //获取需要下载的文件的名字
        String filename = request.getParameter("filename");
        //通过content-disposition来告知浏览器，收到的内容不要去解析，直接以附件的形式下载即可。
        response.setHeader("content-disposition","attachment;filename=" + filename);//x1.zip

        //文件的路径
        String realPath = getServletContext().getRealPath("download/" + filename);
        System.out.println("需要下载的文件的路径:"+realPath);
        //获取文件的io
        InputStream is = new FileInputStream(realPath);

        //将资源以字节的形式响应给浏览器
        byte[] buf = new byte[1024*8];
        int len = -1;
        while((len=(is.read(buf)))!=-1){
            response.getOutputStream().write(buf,0,len);
        }

        is.close();

    }


    public static void main(String[] args) throws Exception {

        //1.tomcat接受到请求，找到对应的servlet，
        //2.创建req,resp
//        HttpServletRequest req = new HttpServletRequest();
//        HttpServletResponse resp = new HttpServletResponse();
        //将http请求的所有的参数全部解析到req对象中了
        // 调用service方法
//        servlet.service(req,resp);
    }
}