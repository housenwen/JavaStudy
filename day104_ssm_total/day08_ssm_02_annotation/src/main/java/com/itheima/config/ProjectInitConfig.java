package com.itheima.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class ProjectInitConfig extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        
        //注册过滤器
        FilterRegistration.Dynamic characterFilter =
                servletContext.addFilter("characterFilter", CharacterEncodingFilter.class);

        //编码配置
        characterFilter.setInitParameter("encoding","utf-8");

        characterFilter.addMappingForUrlPatterns(null,true,"/*");

        super.onStartup(servletContext);
    }

    //spring配置类的位置
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringConfig.class,MyBatisConfig.class};
    }

    //springmvc配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMvcConfig.class};
    }

    //配置dispatcherServlet的映射路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
