package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//@WebFilter(urlPatterns = "/*")
public class DBadWordFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //统一进行脏词的过滤
        final HttpServletRequest request1 = (HttpServletRequest) servletRequest;

        //针对request1的getParameter()方法做增强即可

        ClassLoader classLoader = request1.getClass().getClassLoader();
        Class<?>[] interfaces = request1.getClass().getInterfaces();

        HttpServletRequest proxyRequest =
                (HttpServletRequest) Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        //只针对getParameter方法做增强
                        String methodName = method.getName();
                        if ("getParameter".equals(methodName)){
                            String param = (String) method.invoke(request1,args);

                            String replaceStr = param.replace("卧槽","**");

                            System.out.println("replaceStr"+replaceStr);
                            return replaceStr;
                        }else {
                            return method.invoke(request1,args);
                        }
                    }
                });
        //传递代理对象
        filterChain.doFilter(proxyRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }
}
