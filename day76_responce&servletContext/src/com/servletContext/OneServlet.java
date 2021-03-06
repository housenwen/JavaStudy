package com.servletContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/OneServlet")
public class OneServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
    /*
     * #获取ServletContext对象的两种方式
     * 1. 小域对象可以获取大域对象
     *    ServletContext servletContext = request.getServletContext();
     * 2. servlet对象也可以获取
     *       ServletContext servletContext = this.getServletContext();
     * */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext context1 = request.getServletContext();

//        ServletContext context2 = this.getServletContext();
        ServletContext context2 = getServletContext();

        System.out.println("context1:" + context1);
        System.out.println("context2:" + context2);
        //域对象: 存
        context1.setAttribute("stuff","money");
    }

}