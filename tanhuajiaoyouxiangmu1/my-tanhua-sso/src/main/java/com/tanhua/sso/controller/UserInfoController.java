package com.tanhua.sso.controller;

import com.tanhua.sso.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 基本信息
     *
     * @param param
     * @param token
     * @return
     */
    @PostMapping("loginReginfo")
    public Object loginReginfo(@RequestBody Map<String, Object> param,
                               @RequestHeader("Authorization") String token) {
        return this.userInfoService.loginReginfo(param, token);
    }

    /**
     * 上传头像
     *
     * @param token
     * @param multipartFile
     * @return
     */
    @PostMapping("loginReginfo/head")
    public Object userLogo(@RequestHeader("Authorization") String token,
                           @RequestParam("headPhoto") MultipartFile multipartFile) {
        return this.userInfoService.userLogo(token, multipartFile);
    }
}
