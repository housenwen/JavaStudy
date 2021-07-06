package com.heima.wemedia.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.heima.wemedia.gateway.util.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/5 9:05
 **/
@Component
@Slf4j
@Order(0) // 值越小 优先级越高
public class AuthorizeFilter implements GlobalFilter {

    private List<String> allowUrls = Arrays.asList("/login/in","/v2/api-docs");

    /**
     * @param exchange  封装了 请求  和  响应
     * @param chain   过滤器链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取 请求对象和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 2. 判断当前请求路径是否需要拦截
        String path = request.getURI().getPath();
        for (String allowUrl : allowUrls) {
            if(path.contains(allowUrl)){
                return chain.filter(exchange); // 如果路径在白名单，则放行路径
            }
        }
        // 3. 需要拦截 获取请求头中的token信息
        String token = request.getHeaders().getFirst("token");
        if (StringUtils.isBlank(token)) {
            //  3.1 如果不存在，直接终止请求
            Map<String, Object> map = new HashMap<>();
            map.put("host", request.getRemoteAddress().getHostName());
            map.put("code", HttpStatus.UNAUTHORIZED.value());
            map.put("errorMessage", "需要登录");
            return writeMessage(response,map);
        }
        //  3.2 如果token存在，使用jwt解析token
        try {
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);
            int i = AppJwtUtil.verifyToken(claimsBody);
            if(i < 1){ // token有效

                // 5. 解析成功， 获取token中的用户id,将用户id重新设置header中
                Object id = claimsBody.get("id");
                log.info("网关获取到 userId值: {}",id);
                // 改变request的原有属性
                request.mutate().header("userId",String.valueOf(id));

                // 6. 放行请求
                return chain.filter(exchange);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("网关解析token时出现异常，异常信息: {}",e.getMessage());
        }
        //  3.1 如果不存在，直接终止请求
        Map<String, Object> map = new HashMap<>();
        map.put("host", request.getRemoteAddress().getHostName());
        map.put("code", HttpStatus.UNAUTHORIZED.value());
        map.put("errorMessage", "需要登录");
        return writeMessage(response,map);
    }

    private Mono<Void> writeMessage(ServerHttpResponse response, Map<String, Object> resultMap) {
        //获取响应对象
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        //response.setStatusCode(HttpStatus.OK);
        //设置返回类型
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        //设置返回数据
        DataBuffer buffer = response.bufferFactory().wrap(JSON.toJSONBytes(resultMap));
        //响应数据回浏览器
        return response.writeWith(Flux.just(buffer));
    }
}
