package com.tanhua.dubbo.huanxin.service;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import cn.hutool.json.JSONUtil;
import com.tanhua.dubbo.huanxin.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 针对环信的REST接口编写通用的请求实现
 */
@Service
@Slf4j
public class RequestService {

    @Autowired
    private TokenService tokenService;

    /**
     * 通用的请求
     */
    @Retryable(value = UnauthorizedException.class, maxAttempts = 4, backoff = @Backoff(delay = 2000L, multiplier = 0))
    public HttpResponse execute(String url, Method method, Map<String, Object> param) {
        HttpRequest httpRequest;
        switch (method) {
            case GET: {
                httpRequest = HttpRequest.get(url);
                break;
            }
            case POST: {
                httpRequest = HttpRequest.post(url);
                break;
            }
            case PUT: {
                httpRequest = HttpRequest.put(url);
                break;
            }
            case DELETE: {
                httpRequest = HttpRequest.delete(url);
                break;
            }
            default: {
                return null;
            }
        }

        String body = JSONUtil.toJsonStr(param);

        //发起请求
        HttpResponse response = httpRequest
                .header(Header.CONTENT_TYPE, "application/json")
                //请求中携带token
                .header(Header.AUTHORIZATION, "Bearer " + this.tokenService.getToken())
                .body(body) //请求体参数
                .timeout(20000)//超时，毫秒
                .execute();

        if (response.isOk()) {
            //请求成功了
            return response;
        }

        if (response.getStatus() == 401) {
            //token过期了，环信不保证token在有效期内绝对有效
            // 刷新token
            this.tokenService.refreshToken();
            //重试此次请求，重试一般需要设置次数，比如：设置最多重试3次
            throw new UnauthorizedException(url, body, method);
        }
        //请求出错
        return null;
    }

    @Recover //全部重试失败后执行
    public HttpResponse recover(UnauthorizedException e) {
        //重试全部失败的情况非常可能是被封号了
        //程序已经无法自己修复，需要人工介入
        //程序必须通知相关人员进行排除，如何通知：可以采用短信、邮件等形式进行通知
        //TODO 通知在项目实战中完成
        log.error("环信token重试失败了，请检查 " + e);
        MailAccount mailAccount = new MailAccount();
        MailUtil.send(mailAccount,"1152628322@qq.com","环信token通知","环信token重试失败了，请检查 ",false);

        return null; //返回默认
    }

}
