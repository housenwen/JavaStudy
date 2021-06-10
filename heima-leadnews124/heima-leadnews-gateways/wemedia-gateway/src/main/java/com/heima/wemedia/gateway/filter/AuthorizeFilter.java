package com.heima.wemedia.gateway.filter;

import com.heima.wemedia.gateway.config.GatewayAuthConfig;
import com.heima.wemedia.gateway.util.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/5/24 18:23
 **/
@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter {

    // 不需要拦截的路径
//    private List<String> allowUrls = Arrays.asList(
//            "/login/in"
//    );
    @Autowired
    GatewayAuthConfig gatewayAuthConfig;

    /**
     * 认证过滤方法
     * @param exchange 请求 和 响应对象
     * @param chain  链
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1. 获取请求和响应对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        // 2. 判断请求路径是否需要拦截，不需要直接放行
        String path = request.getURI().getPath(); // 请求路径
        List<String> allowUrls = gatewayAuthConfig.getAllowUrls();
        System.out.println(allowUrls);
        if(allowUrls!=null && allowUrls.size()>0){
            for (String allowUrl : allowUrls) {
                if(path.endsWith(allowUrl)){
                    return chain.filter(exchange); // 不需要拦截的路径直接放行
                }
            }
        }
        // 3. 获取请求头中的token信息
        String token = request.getHeaders().getFirst("token");
        // 4. token不存在 直接 终止请求  返回401
        if (StringUtils.isBlank(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 5. 解析token 解析失败 终止请求  返回401
        try {
            Claims claims = AppJwtUtil.getClaimsBody(token);
            // -1：有效，0：有效，1：过期，2：过期
            int i = AppJwtUtil.verifyToken(claims);
            if(i<1){ // 成功
                Integer userId = (Integer)claims.get("id");
                // 6. 获取到token中存储的userId, 将userId在写到请求头中，转发到其它微服务
                log.info("网关过滤器校验token成功,当前登录用户Id ==> {}",userId);
                ServerHttpRequest newRequest = request.mutate().header("userId", String.valueOf(userId)).build();
                ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
                return chain.filter(newExchange);
            }
        }catch (Exception e){
            log.error("解析token失败 ==>{}",e.getMessage());
        }
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        return response.setComplete();
    }
}
