package com.heima.config;
import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
@Configuration
@EnableFeignClients(basePackages = "com.heima.feigns")
@ComponentScan("com.heima.feigns.fallback")
public class HeimaFeignAutoConfiguration {
    @Bean
    Logger.Level level(){
        return Logger.Level.FULL; // 打印最详细日志 默认;不打印  debug
    }
}