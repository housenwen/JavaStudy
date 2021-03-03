package com.servlet;

import javax.servlet.*;
import javax.servlet.Servlet;
import java.io.IOException;

public class DemoServlet implements Servlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("servlet被初始化的时候调用");

        //获取servlet配置的配置
        String servletName = config.getServletName();
        System.out.println("servlet的配置名:"+servletName);

        //获取servlet的初始化配置参数
        String encoding = config.getInitParameter("encoding");
        System.out.println("servlet中配置的初始化配置参数:"+encoding);

        //获取servletContext对象
        ServletContext servletContext = config.getServletContext();
        System.out.println("servletContext:"+servletContext);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("接收到浏览器的请求，DemoServlet中的代码执行了，");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("server销毁时使用");
    }
}
