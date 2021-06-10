package com.heima.config;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableFeignClients(basePackages = "com.heima.feigns")
public class HeimaFeignAutoConfiguration {

    @Bean
    Logger.Level level(){
        // 打印日志
        return Logger.Level.FULL;
    }
}