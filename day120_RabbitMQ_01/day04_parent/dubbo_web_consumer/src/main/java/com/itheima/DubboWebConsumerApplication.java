package com.itheima;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.itheima")
public class DubboWebConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboWebConsumerApplication.class, args);
    }

}
