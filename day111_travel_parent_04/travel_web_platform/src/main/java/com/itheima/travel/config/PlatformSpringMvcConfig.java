package com.itheima.travel.config;

import com.itheima.travel.interceptors.CrossInterceptor;
import com.itheima.travel.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class PlatformSpringMvcConfig extends  SpringMvcConfig {


    /**
     * @Description 登录拦截
     */
    @Bean("loginInterceptor")
    public LoginInterceptor loginInterceptor(){
        return new LoginInterceptor();
    }


    @Autowired
    private LoginInterceptor loginInterceptor;


    @Bean
    public CrossInterceptor crossInterceptor(){
        return new CrossInterceptor();
    }


    @Autowired
    private CrossInterceptor crossInterceptor;
    /**
     * 资源路径 映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * 支持webjars
         */
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        /**
         * 支持swagger
         */
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        super.addResourceHandlers(registry);
    }


    /**
     * @Description 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //跨域拦截器
       registry.addInterceptor(crossInterceptor).addPathPatterns("/**");

        // 登录拦截
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/**/user/**",
                        "/**/seller/**",
                        "/**/category/**",
                        "/**/webjars/**",
                        "/**/swagger-ui.html",
                        "/**/swagger-resources/**",
                        "/**/v2/**"
                );
    }
}
