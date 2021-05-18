package com.tanhua.server.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.json.JSONUtil;
import com.tanhua.common.utils.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在进入Controller之前进行缓存的命中，如果命中就返回，否则进入Controller执行原有的业务逻辑
 */
@Component
public class RedisCacheInterceptor implements HandlerInterceptor {

    @Value("${tanhua.cache.enable}")
    private Boolean cacheEnable;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        if (!cacheEnable) {
            //如果没有开启缓存，就直接放行
            return true;
        }

        if (!(handler instanceof HandlerMethod)) {
            // 没有匹配到Controller中的方法
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        //判断该方法是否是GET请求
        if (!handlerMethod.hasMethodAnnotation(GetMapping.class)) {
            return true;
        }

        //判断该方法是否包含了@Cache注解
        if (!handlerMethod.hasMethodAnnotation(Cache.class)) {
            return true;
        }

        //进行缓存的命中
        String redisKey = createRedisKey(request);
        String redisData = this.redisTemplate.opsForValue().get(redisKey);
        if (StrUtil.isEmpty(redisData)) {
            //缓存未命中
            return true;
        }

        //说明，缓存已命中，不再执行Controller
        //响应数据
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(redisData);

        return false;
    }

    public static String createRedisKey(HttpServletRequest request) {
        //请求url
        String url = request.getRequestURI();
        //请求参数
        String paramStr = JSONUtil.toJsonStr(request.getParameterMap());
        //请求头
        String token = request.getHeader("Authorization");
        return "TANHUA_CACHE_" + SecureUtil.md5(url + paramStr + token);
    }
}
