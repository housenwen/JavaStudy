package com.itheima.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration //声明这个springmvc配置类
@ComponentScan("com.itheima.controller") //注解扫描
@EnableWebMvc //注解驱动
public class SpringMvcConfig extends WebMvcConfigurationSupport {


    @Override
    protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {

        configurer.enable();//释放静态资源

    }

    //视图解析器
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setSuffix(".jsp");
        viewResolver.setPrefix("/WEB-INF/jsp/");

        return viewResolver;
    }
}
