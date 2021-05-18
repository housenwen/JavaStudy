package com.tanhua.sso.config;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@NacosConfigurationProperties(dataId = "aliyun.properties", prefix = "aliyun.vision", autoRefreshed = true)
@Data
public class AliyunFaceConfig {

    private String accessKeyId;
    private String accessKeySecret;
    private String regionId;

    @Bean
    public IAcsClient iAcsClient() {
        DefaultProfile profile = DefaultProfile.getProfile(
                regionId,
                accessKeyId,
                accessKeySecret);
        return new DefaultAcsClient(profile);
    }

}