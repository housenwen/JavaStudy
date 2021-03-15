package com.itheima.web;

import com.itheima.pojo.PageBean;
import com.itheima.service.ContactService;
import com.itheima.service.impl.ContactServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findContactByPage.do")
public class FindContactByPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.获取pageSize和currentPage
        int pageSize = Integer.parseInt(request.getParameter("pageSize"));
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));


        //获取搜索的参数
        String name = request.getParameter("name");
        String minAgeStr = request.getParameter("minAge");
        int minAge = 0;
        if(!(minAgeStr==null||minAgeStr.equals(""))){
            minAge = Integer.parseInt(minAgeStr);
        }
        String maxAgeStr = request.getParameter("maxAge");
        int maxAge = 0;
        if(!(maxAgeStr==null||maxAgeStr.equals(""))){
            maxAge = Integer.parseInt(maxAgeStr);
        }

        //2.调用service查询分页的页面需要的数据
        ContactService contactService = new ContactServiceImpl();
        PageBean pageBean = contactService.findContactByPage(currentPage,pageSize,name,minAge,maxAge);

        //3.转发到list2.jsp
        request.setAttribute("pageBean",pageBean);

        //记住上一次的值
        request.setAttribute("name",name);
        request.setAttribute("minAge",minAge);
        request.setAttribute("maxAge",maxAge);

        request.getRequestDispatcher("list2.jsp").forward(request,response);

    }
}