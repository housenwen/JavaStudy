package com.itheima;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
/**
 * 开启对子Dubbo注解的支持
 */
@EnableDubbo(scanBasePackages = "com.itheima")
public class Day0303BootServiceProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Day0303BootServiceProducerApplication.class, args);
    }

}
