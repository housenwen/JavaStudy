package com.heima.cache.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
@Configuration
public class RedisConfiguration {
    /**
     * 配置redisTemplate
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    RedisTemplate<String,String> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String,String> redisTemplate = new RedisTemplate<>();
        // 设置redis连接信息
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置开启事务支持
        redisTemplate.setEnableTransactionSupport(true);
        // 设置key的序列化方式
        redisTemplate.setKeySerializer(RedisSerializer.string());
        // 设置value的序列化方式
        redisTemplate.setValueSerializer(RedisSerializer.string());
        return redisTemplate;
    }
}