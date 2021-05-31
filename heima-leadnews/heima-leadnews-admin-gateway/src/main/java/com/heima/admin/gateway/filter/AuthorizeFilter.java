package com.heima.admin.gateway.filter;

import com.heima.admin.gateway.utils.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Log4j2
public class AuthorizeFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求对象
        ServerHttpRequest request = exchange.getRequest();
        //2.获取响应对象
        ServerHttpResponse response = exchange.getResponse();

        //3.判断当前的请求是否为登录请求,如果是,则直接放行
        if (request.getURI().getPath().contains("/login/in")){
            //放行
            return chain.filter(exchange);
        }
        //4.获取当前的所有请求头信息
        HttpHeaders headers = request.getHeaders();

        //5.获取jwt令牌信息
        String jwtToken = headers.getFirst("token");

        //6.判断当前令牌是否存在,
        if (StringUtils.isEmpty(jwtToken)){
            //如果不存在,则向客户端返回错误提示信息
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //6.1 如果令牌存在,解析jwt令牌,判断该令牌是否合法,如果令牌不合法,则向客户端返回错误提示信息
        try {
            //解析令牌
            Claims claims = AppJwtUtil.getClaimsBody(jwtToken);
            int result = AppJwtUtil.verifyToken(claims);
            Integer userId = (Integer) claims.get("id");
            if (result == 0 || result == -1) {
                log.info("find userid:[{}] from uri:{}", userId, request.getURI());
                if (userId != null) {
                    // 重新设置TOKEN
                    request.mutate().headers(httpHeaders -> {
                        httpHeaders.add("userId",userId+"");
                    });
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            //令牌解析失败
            //向客户端返回错误提示信息
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //6.2 如果令牌合法,则放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}