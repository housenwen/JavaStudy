package com.itheima.web;

import com.itheima.pojo.PageBean;
import com.itheima.service.ContactService;
import com.itheima.utils.BeansFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findContactByPage")
public class FindContactByPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取参数,当前页码数currentPage,每页显示的数量 pageSize
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        //多获取3个参数
        String name = request.getParameter("name");
        String min = request.getParameter("min");
        String max = request.getParameter("max");


        //初始化分页查询的默认值
        int currentPage = 1;
        int pageSize = 3;
        //类型转换
        if (currentPageStr!=null){
            currentPage = Integer.parseInt(currentPageStr);
        }

        if(pageSizeStr!=null){
            pageSize = Integer.parseInt(pageSizeStr);
        }


        //2.调用service
        ContactService contactService = (ContactService) BeansFactory.getBean("contactService");
        //PageBean pageBean = contactService.findContactByPage(currentPage,pageSize);
        PageBean pageBean = contactService.findContactByPage(currentPage,pageSize,name,min,max);




        //3.转发到list2.jsp
        request.setAttribute("pageBean",pageBean);
        request.getRequestDispatcher("list2.jsp").forward(request,response);
    }
}