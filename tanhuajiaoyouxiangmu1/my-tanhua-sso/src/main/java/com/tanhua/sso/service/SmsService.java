package com.tanhua.sso.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsResponseBody;
import com.aliyun.teaopenapi.models.Config;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.sso.config.AliyunSMSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
public class SmsService {

    @Autowired
    private AliyunSMSConfig aliyunSMSConfig;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // @PostConstruct
    // public void test(){
    //     for (int i = 0; i < 99999999; i++) {
    //         System.out.println("######### -> " + this.aliyunSMSConfig);
    //         try {
    //             Thread.sleep(500);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    /**
     * 发送短信验证码
     *
     * @param mobile
     * @return
     */
    public String sendSms(String mobile) {
        //随机生成6位数字验证码
        String code = RandomUtil.randomNumbers(6);
        try {
            Config config = new Config()
                    .setAccessKeyId(this.aliyunSMSConfig.getAccessKeyId())
                    .setAccessKeySecret(this.aliyunSMSConfig.getAccessKeySecret())
                    .setEndpoint(this.aliyunSMSConfig.getDomain());

            Client client = new Client(config);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(mobile)//目标手机号
                    .setSignName(this.aliyunSMSConfig.getSignName()) //签名名称
                    .setTemplateCode(this.aliyunSMSConfig.getTemplateCode()) //短信模板code
                    .setTemplateParam("{\"code\":\"" + code + "\"}"); //模板中变量替换
            SendSmsResponse sendSmsResponse = client.sendSms(sendSmsRequest);
            SendSmsResponseBody body = sendSmsResponse.getBody();
            if (StrUtil.equals("OK", body.getCode())) {
                return code;
            }
        } catch (Exception e) {
            log.error("发送短信验证码失败！" + mobile, e);
        }
        return null;
    }

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    public ErrorResult sendCheckCode(String phone) {
        String redisKey = "CHECK_CODE_" + phone;
        //判断之前发送的验证码是否还有效，如果有效就不能再次发送
        if (this.redisTemplate.hasKey(redisKey)) {
            return ErrorResult.builder().errCode("5002").errMessage("上一次发送的验证码还未失效，请稍后再试！").build();
        }

        //真正的发送短信
        // String code = this.sendSms(phone);
        String code = "123456";
        if (StrUtil.isEmpty(code)) {
            //短信发送失败
            return ErrorResult.builder().errCode("5001").errMessage("发送验证码失败！").build();
        }

        //短信发送成功，将验证码存储到redis中，有效期为5分钟
        this.redisTemplate.opsForValue().set(redisKey, code, Duration.ofMinutes(5));
        return null;
    }
}
