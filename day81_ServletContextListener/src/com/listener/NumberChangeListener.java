package com.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class NumberChangeListener implements HttpSessionListener {

    // 会话建立，在线人数+1
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // 获取session域对象
        HttpSession session = httpSessionEvent.getSession();
        // 获取上下文域对象
        ServletContext servletContext = session.getServletContext();
        // 取出在线人数
        Integer number = (Integer) servletContext.getAttribute("number");
        // +1
        servletContext.setAttribute("number", number + 1);
    }

    // 会话销毁，在线人数-1
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        // 获取session域对象
        HttpSession session = httpSessionEvent.getSession();
        // 获取上下文域对象
        ServletContext servletContext = session.getServletContext();
        // 取出在线人数
        Integer number = (Integer) servletContext.getAttribute("number");
        // -1
        servletContext.setAttribute("number", number - 1);
    }
}

