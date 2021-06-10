package com.heima.article.config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
@Configuration
@Slf4j
public class MyRedisConfig {
   @Bean
   public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
      RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(new StringRedisSerializer());
      redisTemplate.setConnectionFactory(redisConnectionFactory);
      log.info("-------RedisTemplate init--------");
      return redisTemplate;
   }
}