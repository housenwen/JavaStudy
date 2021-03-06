package com.itheima.response;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = "/bodyServlet2")
public class BodyServlet2 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /**
         * 字节流响应体
         *   response.getOutputStream(); 获取字节流响应体
         */

        //1.获取图片发布后所在的路径
        String realPath = getServletContext().getRealPath("img/1.png");
        System.out.println("图片的路径:"+realPath);
        //2.读取图片，获取图片的io流对象
        InputStream is = new FileInputStream(new File(realPath));
        //3.将图片转换成字节，响应给浏览器
        byte[] buf = new byte[1024];
        int len = -1;
        while ((len=(is.read(buf)))!=-1){
            //通过字节流响应体，响应给浏览器
            response.getOutputStream().write(buf,0,len);
        }
        //释放io流
        is.close();
    }
}