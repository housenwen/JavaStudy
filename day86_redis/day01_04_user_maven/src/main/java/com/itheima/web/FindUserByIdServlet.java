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

@WebServlet(urlPatterns = "/findUserById.do")
public class FindUserByIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取id
        String id = request.getParameter("id");
        //2.调用service查询
        ContactService contactService = new ContactServiceImpl();
        Contact contact = contactService.findUserById(id);
        //3.转发到update.jsp
        request.setAttribute("contact",contact);
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
}