package com.tanhua.sso.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
//从nacos配置中心读取内容，并且支持自动刷新功能
@NacosConfigurationProperties(dataId = "aliyun.properties", prefix = "aliyun.sms",
        autoRefreshed = true)
@Data
public class AliyunSMSConfig {

    private String accessKeyId;
    private String accessKeySecret;
    private String domain;
    private String signName;
    private String templateCode;

}