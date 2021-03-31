package com.itheima.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration //声明配置类
@ComponentScan("com.itheima") //ioc注解扫描
@EnableAspectJAutoProxy //开启aop注解
public class SpringConfig {
}
