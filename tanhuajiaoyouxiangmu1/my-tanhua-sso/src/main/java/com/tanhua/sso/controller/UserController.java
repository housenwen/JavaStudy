package com.tanhua.sso.controller;

import com.tanhua.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验验证码
     *
     * @param param
     * @return
     */
    @PostMapping("loginVerification")
    public Object loginVerification(@RequestBody Map<String, String> param) {
        String phone = param.get("phone");
        String code = param.get("verificationCode");
        return this.userService.loginVerification(phone, code);
    }

}
