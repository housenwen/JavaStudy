package com.itheima.web;

import com.itheima.pojo.Contact;
import com.itheima.service.ContactService;
import com.itheima.utils.BeansFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findAllContact")
public class FindAllContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.调用service
        ContactService contactService = (ContactService)BeansFactory.getBean("contactService");
        List<Contact> list =  contactService.findAllContact();


        //2.转发
        request.setAttribute("list",list);
        request.getRequestDispatcher("list.jsp").forward(request,response);

    }
}