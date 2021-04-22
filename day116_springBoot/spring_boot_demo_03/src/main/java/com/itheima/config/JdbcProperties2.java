package com.itheima.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ToString
@ConfigurationProperties(prefix = "jdbc")
@Component  //用于创建对象
public class JdbcProperties2 {

    private String username;
    private String password;
}
