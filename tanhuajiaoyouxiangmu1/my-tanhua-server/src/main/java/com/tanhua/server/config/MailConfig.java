package com.tanhua.server.config;

import cn.hutool.extra.mail.MailAccount;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Data
@Configuration
@NacosConfigurationProperties(dataId = "mail.properties", prefix = "mail.vision", autoRefreshed = true)
public class MailConfig {

    private String host;
    private Integer port;
    private String username;
    private String password;
    private String from;

    @Bean
    public MailAccount account(){
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(host);
        mailAccount.setPort(port);
        mailAccount.setPass(password);
        mailAccount.setUser(username);
        mailAccount.setFrom(from);
        mailAccount.setAuth(true);
        mailAccount.setSslEnable(false);
        return mailAccount;
    }
}
