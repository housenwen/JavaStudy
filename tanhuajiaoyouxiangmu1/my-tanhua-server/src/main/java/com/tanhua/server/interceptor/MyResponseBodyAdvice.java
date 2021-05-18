package com.tanhua.server.interceptor;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.tanhua.common.utils.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.concurrent.TimeUnit;

/**
 * 将Controller中返回的数据写入到Redis中
 */
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    @Value("${tanhua.cache.enable}")
    private Boolean cacheEnable;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return cacheEnable && methodParameter.hasMethodAnnotation(GetMapping.class)
                && methodParameter.hasMethodAnnotation(Cache.class);
    }

    @Override
    public Object beforeBodyWrite(Object result, MethodParameter methodParameter, MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (null == result) {
            return null;
        }

        String cacheData;
        if (result instanceof String) {
            cacheData = (String) result;
        } else {
            cacheData = JSONUtil.toJsonStr(result);
        }

        String redisKey = RedisCacheInterceptor
                .createRedisKey(((ServletServerHttpRequest) serverHttpRequest).getServletRequest());

        //获取@Cache注解中的时间
        Cache cache = methodParameter.getMethodAnnotation(Cache.class);
        long time = Convert.toLong(cache.time());
        this.redisTemplate.opsForValue().set(redisKey, cacheData, time, TimeUnit.SECONDS);

        return result;
    }
}
