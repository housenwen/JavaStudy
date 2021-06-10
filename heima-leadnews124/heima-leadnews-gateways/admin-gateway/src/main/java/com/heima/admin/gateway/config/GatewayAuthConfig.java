package com.heima.admin.gateway.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * @作者 itcast
 **/
@Component
@ConfigurationProperties(prefix = "spring.cloud.gateway.custom-config")
@Data
public class GatewayAuthConfig {
    private List<String> blackList; // IP黑名单
    private List<String> allowUrls; // 放行url路径
}