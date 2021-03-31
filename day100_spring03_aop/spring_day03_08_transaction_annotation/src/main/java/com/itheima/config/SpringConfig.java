package com.itheima.config;

import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.itheima")
@Import(JdbcConfig.class)
@EnableTransactionManagement //开启注解事务
public class SpringConfig {
}