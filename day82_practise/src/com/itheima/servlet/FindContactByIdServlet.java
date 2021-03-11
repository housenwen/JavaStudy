package com.itheima.servlet;

import com.itheima.pojo.Contact;
import com.itheima.service.ContactService;
import com.itheima.utils.BeansFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/findContactById")
public class FindContactByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        //int id = Integer.parseInt("idStr");
        ContactService contactService = (ContactService) BeansFactory.getBean("contactService");
        Contact contact = contactService.findContactById(idStr);
        request.setAttribute("contact",contact);
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
}