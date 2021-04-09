package com.itheima.travel.config;


import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration //声明当前类是一个配置类
@ComponentScan(value = "com.itheima",
        excludeFilters = @ComponentScan.Filter(Controller.class)) //注解扫描，排除注解Controller
@PropertySource("classpath:jdbc.properties") //加载外部配置
@Import(MyBatisConfig.class)
@EnableTransactionManagement //开启注解事务
@EnableAspectJAutoProxy //开启注解aop
public class SpringConfig {
}