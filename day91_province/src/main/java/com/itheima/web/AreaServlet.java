package com.itheima.web;

import com.itheima.service.impl.AreaService;
import com.itheima.service.impl.AreaServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/areaServlet")
public class AreaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


//        //通过设置响应头，允许跨域访问
//        response.setHeader("Access-Control-Allow-Origin","*"); //所有远程可以跨域
//        response.setHeader("Access-Control-Allow-Method","*");// 所有方式可以跨域


//        response.setContentType("text/html;charset=utf-8");
//
//        //1.获取父级区域的pid
//        String pid = request.getParameter("pid");
//
//        //2.调用service
//        AreaService areaService = new AreaServiceImpl();
//        //service直接转换成json字符串
//        String areaJsonStr =  areaService.findAreaByPid(pid);
//
//        //3.响应给浏览器
//        response.getWriter().write(areaJsonStr);

        response.setContentType("text/html;charset=utf-8");
        //1.获取父级区域的pid
        String pid = request.getParameter("pid");
        //2.调用service
        AreaService areaService = new AreaServiceImpl();
        //service直接转换成json字符串
        String areaJsonStr = areaService.findAreaByPid(pid);

        //3.响应给浏览器
        response.getWriter().write(areaJsonStr);


    }
}