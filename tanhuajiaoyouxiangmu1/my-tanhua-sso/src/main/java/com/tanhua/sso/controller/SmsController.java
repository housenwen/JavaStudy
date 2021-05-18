package com.tanhua.sso.controller;

import com.tanhua.common.vo.ErrorResult;
import com.tanhua.sso.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 短信验证码相关逻辑
 */
@RestController
@RequestMapping("user")
public class SmsController {

    @Autowired
    private SmsService smsService;

    /**
     * 发送短信验证码
     *
     * @param param
     */
    @PostMapping("login")
    public ErrorResult sendCheckCode(@RequestBody Map<String, String> param) {
        String phone = param.get("phone");
        return this.smsService.sendCheckCode(phone);
    }

}
