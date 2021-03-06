package com.responce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/BServlet")
public class BServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("赵老师说: 我很有经验,首先得这样,然后再这样...");
        //避免响应体中文乱码
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print("<<恋爱大法>>"+"赵老师说: 我很有经验,首先得这样,然后再这样...");
    }
}

