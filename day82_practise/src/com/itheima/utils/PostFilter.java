package com.itheima.utils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "*.do")
public class PostFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String method = request.getMethod();
        if("POST".equalsIgnoreCase(method)){
            request.setCharacterEncoding("utf-8");
        }
        chain.doFilter(req, resp);
    }

    public void destroy() {

    }
}
