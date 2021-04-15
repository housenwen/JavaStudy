package com.itheima.travel.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ProjectInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

        //注册过滤器
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("characterEncodingFilter", new CharacterEncodingFilter());

        //配置过滤器的初始化参数
        encodingFilter.setInitParameter("encoding","utf-8");

        //配置过滤器的映射路径
        encodingFilter.addMappingForUrlPatterns(null,false,"/*");

        super.onStartup(servletContext);
    }

    //spring的配置类,监听器根据配置类创建spring容器，存放到servletContext域
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    //springmvc的配置类,dispathcerServlet根据springmvc的配置创建springmvc的容器
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{PlatformSpringMvcConfig.class};
    }

    // 前端控制器DispatcherServlet的映射路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}