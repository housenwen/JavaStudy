package com.tanhua.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync //开启异步的支持
public class QuanZiApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuanZiApplication.class, args);
    }
}
