package com.servletContext;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TwoServlet")
public class TwoServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context3 = request.getServletContext();
        System.out.println("context3 :" + context3);

        Object stuff = context3.getAttribute("stuff");

        System.out.println("取出来:" + stuff);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write("取出来:" + stuff);
    }

}