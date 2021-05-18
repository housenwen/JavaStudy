package com.tanhua.dubbo.huanxin.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tanhua.dubbo.huanxin.config.HuanXinConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TokenService {

    private static final String REDIS_KEY = "HX_TOKEN";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private HuanXinConfig huanXinConfig;

    /**
     * 获取token
     *
     * @return
     */
    public String getToken() {
        //先从缓存中命中，如果命中就返回，否则去环信刷token
        String token = this.redisTemplate.opsForValue().get(REDIS_KEY);
        if (StrUtil.isNotEmpty(token)) {
            return token;
        }

        //环信刷token
        return this.refreshToken();
    }

    /**
     * 刷新token，请求环信接口，将token存储到redis中
     *
     * @return
     */
    public String refreshToken() {
        String url = StrUtil.format("{}{}/{}/token",
                this.huanXinConfig.getUrl(),
                this.huanXinConfig.getOrgName(),
                this.huanXinConfig.getAppName());

        //构造请求体
        Map<String, Object> param = new HashMap<>();
        param.put("grant_type", "client_credentials");
        param.put("client_id", this.huanXinConfig.getClientId());
        param.put("client_secret", this.huanXinConfig.getClientSecret());

        //发起请求
        HttpResponse response = HttpRequest.post(url)
                .header(Header.CONTENT_TYPE, "application/json")
                .body(JSONUtil.toJsonStr(param)) //请求体参数
                .timeout(20000)//超时，毫秒
                .execute();

        if (!response.isOk()) {
            //刷新token失败
            return null;
        }

        //响应的json
        String body = response.body();
        JSONObject jsonObject = JSONUtil.parseObj(body);
        String token = jsonObject.getStr("access_token");
        //获取token的有效期时间
        Long time = jsonObject.getLong("expires_in") - 3600;

        //将token写入到redis中
        this.redisTemplate.opsForValue().set(REDIS_KEY, token, time, TimeUnit.SECONDS);

        return token;
    }


}
