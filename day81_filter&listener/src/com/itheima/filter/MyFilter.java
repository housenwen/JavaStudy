package com.itheima.filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器初始化时调用");

        String filterName = filterConfig.getFilterName();
        System.out.println("过滤器的名字："+filterName);

        ServletContext servletContext = filterConfig.getServletContext();
        System.out.println("servletContext域对象:"+servletContext);

        String encoding = filterConfig.getInitParameter("encoding");
        System.out.println("过滤器中配置的乱码处理的编码:"+encoding);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("过滤器执行了，例如做乱码的统一处理。。。。");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("过滤器销毁时调用");
    }
}
