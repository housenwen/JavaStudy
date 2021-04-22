package com.itheima;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itheima.dao")
@EnableDubbo(scanBasePackages = "com.itheima")
public class DubboServiceProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServiceProducerApplication.class, args);
    }

}
