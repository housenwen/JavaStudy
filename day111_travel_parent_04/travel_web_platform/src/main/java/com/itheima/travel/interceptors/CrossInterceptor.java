package com.itheima.travel.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description：
 */
public class CrossInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //解决跨域问题
        String origin = request.getHeader("Origin");
        //服务器设置允许跨域的  域名。
        response.setHeader("Access-Control-Allow-Origin", origin);
        response.setHeader("access-control-allow-methods", "*");
        //是否支持cookie跨域
        response.setHeader("access-control-allow-credentials", "true");
        //指定头部跨域传递accessToken
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        return true;
    }

}