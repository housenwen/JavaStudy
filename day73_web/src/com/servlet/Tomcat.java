package com.servlet;

import java.util.ResourceBundle;

public class Tomcat {
    public static void main(String[] args) throws Exception {

        //浏览器发请求,tomcat接受到这个请求
        String url = "http://localhost:8080/login";
        System.out.println("tomcat接受到浏览器发出的登录请求");

        //需要调用登录方法来处理这个请求
//        LoginServlet loginServlet = new LoginServlet();
//        loginServlet.service();

//        Servlet servlet = new LoginServlet();

        Servlet servlet = null;

        ResourceBundle rb = ResourceBundle.getBundle("web");
        String classPath = rb.getString("classPath");

        //采用反射机制来创建对象
        servlet = (Servlet) Class.forName(classPath).newInstance();

        servlet.service();


    }
}
