package com.itheima.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@ToString
@Configuration //声明配置，spring会创建这个类型的对象
public class JdbcProperties1 {


    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;
}
