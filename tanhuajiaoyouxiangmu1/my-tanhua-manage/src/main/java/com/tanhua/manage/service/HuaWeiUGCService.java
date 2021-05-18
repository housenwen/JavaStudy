package com.tanhua.manage.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.tanhua.manage.enums.AutoAuditStateEnum;
import com.tanhua.manage.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class HuaWeiUGCService {

    @Value("${huaweicloud.token.url}")
    private String TOKEN_URL;

    @Value("${huaweicloud.token.domain}")
    private String TOKEN_DOMAIN;

    @Value("${huaweicloud.token.name}")
    private String TOKEN_NAME;

    @Value("${huaweicloud.token.password}")
    private String TOKEN_PASSWORD;

    @Value("${huaweicloud.token.project}")
    private String TOKEN_PROJECT;

    @Value("${huaweicloud.moderation.categories.image}")
    private String CATEGORIES_IMAGE;

    @Value("${huaweicloud.moderation.categories.text}")
    private String CATEGORIES_TEXT;

    @Value("${huaweicloud.text.check.url}")
    private String TEXT_CHECK_URL;

    @Value("${huaweicloud.image.check.url}")
    private String IMAGE_CHECK_URL;

    public static int connectionTimeout = 5000; //连接目标url超时限制参数
    public static int socketTimeout = 5000;//获取服务器响应数据超时限制参数

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String CACHE_HUAWEI_UGC_TOKEN = "CACHE_HUAWEI_UGC_TOKEN";

    /**
     * 获取token
     *
     * @return
     */
    private String getToken() {
        String token = this.redisTemplate.opsForValue().get(CACHE_HUAWEI_UGC_TOKEN);
        if (StrUtil.isNotEmpty(token)) {
            return token;
        }
        String reqBody = JSONUtil.createObj().set("auth", JSONUtil.createObj()
                .set("identity", JSONUtil.createObj()
                        .set("methods", JSONUtil.createArray().set("password"))
                        .set("password", JSONUtil.createObj()
                                .set("user", JSONUtil.createObj()
                                        .set("domain", JSONUtil.createObj().set("name", TOKEN_DOMAIN))
                                        .set("name", TOKEN_NAME)
                                        .set("password", TOKEN_PASSWORD)
                                )
                        )
                )
                .set("scope", JSONUtil.createObj()
                        .set("project", JSONUtil.createObj()
                                .set("name", TOKEN_PROJECT)
                        )
                )
        ).toString();

        token = HttpRequest.post(TOKEN_URL)
                .contentType("application/json;charset=utf8")
                .setConnectionTimeout(connectionTimeout)
                .setReadTimeout(socketTimeout)
                .body(reqBody)
                .execute()
                .header("X-Subject-Token");

        if (StrUtil.isNotEmpty(token)) {
            this.redisTemplate.opsForValue().set(CACHE_HUAWEI_UGC_TOKEN, token, Duration.ofHours(23));
            return token;
        }
        throw new BusinessException("华为云获取token失败，请检查服务是否已经欠费~");
    }

    /**
     * 文字审核
     *
     * @param textModeration 待审核内容
     * @return 审核结果，block：包含敏感信息，不通过，pass：不包含敏感信息，通过，review：需要人工复查
     */
    public AutoAuditStateEnum textContentCheck(String textModeration) {
        if(StrUtil.isEmpty(textModeration)){
            return AutoAuditStateEnum.REVIEW;
        }
        String reqBody = JSONUtil.createObj()
                .set("categories", StrUtil.split(CATEGORIES_TEXT, ','))
                .set("items", JSONUtil.createArray()
                        .set(JSONUtil.createObj()
                                .set("text", textModeration)
                                .set("type", "content")
                        )
                ).toString();

        log.info("文字审核 ：" + reqBody);

        String resBody = HttpRequest.post(TEXT_CHECK_URL)
                .header("X-Auth-Token", this.getToken())
                .contentType("application/json;charset=utf8")
                .setConnectionTimeout(connectionTimeout)
                .setReadTimeout(socketTimeout)
                .body(reqBody)
                .execute()
                .body();

        JSONObject jsonObject = JSONUtil.parseObj(resBody);
        if (jsonObject.containsKey("result") && jsonObject.getJSONObject("result").containsKey("suggestion")) {
            //获取建议
            return AutoAuditStateEnum.valueOf(jsonObject.getJSONObject("result").getStr("suggestion").toUpperCase());
        }

        //默认人工审核
        return AutoAuditStateEnum.REVIEW;
    }

    /**
     * 图片审核
     *
     * @param urls 图片链接数组
     * @return 审核结果，block：包含敏感信息，不通过，pass：不包含敏感信息，通过，review：需要人工复查
     */
    public AutoAuditStateEnum imageContentCheck(String[] urls) {
        if(ArrayUtil.isEmpty(urls)){
            return AutoAuditStateEnum.REVIEW;
        }
        String reqBody = JSONUtil.createObj()
                .set("categories", CATEGORIES_IMAGE.split(","))
                .set("urls", urls)
                .toString();

        log.info("图片审核 ：" + reqBody);

        String resBody = HttpRequest.post(IMAGE_CHECK_URL)
                .header("X-Auth-Token", this.getToken())
                .contentType("application/json;charset=utf8")
                .setConnectionTimeout(connectionTimeout)
                .setReadTimeout(socketTimeout)
                .body(reqBody)
                .execute()
                .body();

        JSONObject jsonObject = JSONUtil.parseObj(resBody);
        if(jsonObject.containsKey("result")){
            //审核结果中如果出现一个block或review，整体结果就是不通过，如果全部为PASS就是通过
            if(StrUtil.contains(resBody, "\"suggestion\":\"block\"")){
                return AutoAuditStateEnum.BLOCK;
            }else if(StrUtil.contains(resBody, "\"suggestion\":\"review\"")){
                return AutoAuditStateEnum.REVIEW;
            }else{
                return AutoAuditStateEnum.PASS;
            }
        }

        //默认人工审核
        return AutoAuditStateEnum.REVIEW;
    }

}
