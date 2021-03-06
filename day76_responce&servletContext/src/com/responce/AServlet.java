package com.responce;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AServlet")
public class AServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("我说: loser,我没有经验,让他找赵老师");
        //重定向(302 + location)
        //表示重定向
//        response.setStatus(302);
        //重定向的地址
//        response.setHeader("location","/day10-response/BServlet");
//        response.setHeader("location","http://www.baidu.com");

        //简化api (302+location)
        response.sendRedirect("/BServlet");
    }

}