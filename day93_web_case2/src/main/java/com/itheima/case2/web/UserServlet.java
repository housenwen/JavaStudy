package com.itheima.case2.web;

import com.itheima.case2.service.UserService;
import com.itheima.case2.service.impl.UserServiceImpl;
import com.itheima.case2.utils.BeansFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(urlPatterns = "/userServlet")
public class UserServlet extends BaseServlet {



    //分页查询用户
    public void findUserByPage(HttpServletRequest request,HttpServletResponse response){



        int currentPage = 1;
        int pageSize = 5;
        //1.获取参数 ，currentPage,pageSize
        String currentPageStr = request.getParameter("pageNum");
        String pageSizeStr = request.getParameter("pageSize");
        if(currentPageStr!=null){
            currentPage = Integer.parseInt(currentPageStr);
        }

        if(pageSizeStr!=null){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //2.调用service分页查询，返回值String,
        //UserService userService = new UserServiceImpl();
        UserService userService = (UserService) BeansFactory.getBean("userService");
        String jsonStr =  userService.findUserByPage(currentPage,pageSize);


        //3.响应给浏览器
        try {
            response.getWriter().write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    // http://localhost:8080/userServlet?methodName=login
//    /*
//        请求行：
//        请求头：
//        请求体： 请求的参数
//     */
//    public void login(HttpServletRequest request,HttpServletResponse response){
//        System.out.println("登录");
//    }
//
//    // http://localhost:8080/userServlet?methodName=register
//    public void register(HttpServletRequest request,HttpServletResponse response){
//        System.out.println("注册");
//    }
}