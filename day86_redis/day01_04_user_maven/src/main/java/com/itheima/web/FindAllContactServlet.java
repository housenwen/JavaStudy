package com.itheima.web;

import com.itheima.pojo.Contact;
import com.itheima.service.ContactService;
import com.itheima.service.impl.ContactServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/findAllContact.do")
public class FindAllContactServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.直接调用service查询所有联系人
        ContactService userService = new ContactServiceImpl();
        List<Contact> contactList = userService.findAllContact();
        //2.转发到list.jsp
        request.setAttribute("list",contactList);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }
}