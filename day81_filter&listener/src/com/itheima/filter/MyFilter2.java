package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/*
    @WebFilter ：告知了tomcat有这么一个过滤器。tomcat就能创建过滤器对象了。
        filterName： 过滤器的名字，可以不写，有默认值。

 */

@WebFilter(filterName = "MyFilter2",urlPatterns = {"/*"})
public class MyFilter2 implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        System.out.println("过滤器111111执行了，例如做乱码的统一处理。。。。");
        //放行
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}