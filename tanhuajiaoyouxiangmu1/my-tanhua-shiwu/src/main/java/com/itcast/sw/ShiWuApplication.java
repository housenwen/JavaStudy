package com.itcast.sw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;
@SpringBootApplication
//开启定时任务
@EnableScheduling
@EnableRetry
public class ShiWuApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShiWuApplication.class, args);
    }

}
