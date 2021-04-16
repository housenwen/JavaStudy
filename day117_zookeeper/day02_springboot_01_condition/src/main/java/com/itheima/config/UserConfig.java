package com.itheima.config;

import com.itheima.condition.MyClassCondition;
import com.itheima.condition.UserCondition;
import com.itheima.pojo.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration // 声明配置类
public class UserConfig {

    // 创建User对象,存放到IOC容器中
    /*@Bean("user")
    public User getUser(){
        return new User(18,"柳岩");
    }*/

    /**
     * 需求: 当项目中导入jedis的包时,创建User对象
     */
    /*@Bean("user")
    @Conditional({UserCondition.class})
    public User getUser(){
        return new User(18,"柳岩");
    } */

    /**
     * 需求: 当项目中导入jedis的包时,创建User对象
     * 需求: 当项目中导入jedis的包和Junit的包时,创建User对象
     * 多个判断条件,且判断条件不固定
     *      org.junit.Test
     *      redis.clients.jedis.Jedis
     *
     */
    @Bean("user")
    @MyClassCondition({"org.junit.Test","redis.clients.jedis.Jedis"})
    public User getUser(){
        return new User(18,"柳岩");
    }

    /**
     * 判断指定属性是否存在,如果属性存放则创建,否则不创建
     * @return
     */
    @Bean("user1")
    @ConditionalOnProperty({"data_str"})
    public User getUser1(){
        return new User(18,"大黄");
    }
}
