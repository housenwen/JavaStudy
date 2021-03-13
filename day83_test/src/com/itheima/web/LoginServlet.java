package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        //校验验证码
        String serverCode = (String)request.getSession().getAttribute("code_session");
        String userCode = request.getParameter("userCode");

        if(!(serverCode!=null&&serverCode.equalsIgnoreCase(userCode))){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("验证码错误");
            return;//结束当前方法，不能往下走了
        }


        //1.获取请求中的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //2.调用service处理登录请求
        UserService userService = new UserService();
        User user = userService.login(username,password);

        System.out.println(user);

        //3.给出响应
        if(user==null){
            //登录失败
            response.sendRedirect("error.html");
        }else{
            //登录成功

            //判断用户是否勾选了记住选项
            String remember = request.getParameter("remember");
            if("remember".equals(remember)){
                //如果勾选，创建cookie，让浏览器记住用户名和密码
                Cookie usernameCookie = new Cookie("username",username);
                Cookie passwordCookie = new Cookie("password",password);

                //设置最大存活时间
                usernameCookie.setMaxAge(60*60);
                passwordCookie.setMaxAge(60*60);

                //响应给浏览器
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }else{
                //如果没有勾选，删除cookie
                //如果勾选，创建cookie，让浏览器记住用户名和密码
                Cookie usernameCookie = new Cookie("username","");
                Cookie passwordCookie = new Cookie("password","");

                //设置最大存活时间
                usernameCookie.setMaxAge(0);
                passwordCookie.setMaxAge(0);

                //响应给浏览器
                response.addCookie(usernameCookie);
                response.addCookie(passwordCookie);
            }

            //登录成功的情况下，需要将用户信息保存到session
            request.getSession().setAttribute("user",user);

            response.sendRedirect("success.html");
        }
        System.out.println("aaa");
    }
}
