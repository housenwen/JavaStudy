package com.itheima.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Data
@ToString
@ConfigurationProperties(prefix = "jdbc")
public class JdbcProperties3 {

    private String username;
    private String password;

    private Map<String,Object> map;

    private Dog dog;

    private List<String> girls;


}
