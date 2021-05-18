package com.tanhua.sso.controller;

import com.tanhua.sso.service.MyCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "users")
public class MyCenterController {

    @Autowired
    private UserInfoController userInfoController;

    @Autowired
    private MyCenterService myCenterService;


    public Object saveLogo(@RequestParam("headPhoto")MultipartFile file,
                           @RequestHeader("Authorization") String token){
        return this.userInfoController.userLogo(token,file);
    }

    /**
     *
     * @param token
     */
    @PostMapping("phone/sendVerificationCode")
    public void sendVerificationCode(@RequestHeader("Authorization")String token){

        this.myCenterService.sendVerificationCode(token);
    }

    /**
     *校验验证码
     * @return
     */
    @PostMapping("phone/checkVerificationCode")
    public Map<String,Object> checkVerificationCode(@RequestBody Map<String,String> param,
                                                    @RequestHeader("Authorization") String token){
        String code = param.get("verificationCode");
        Boolean aBool= this.myCenterService.checkVerificationCode(code,token);
        Map<String,Object> result = new HashMap<>();
        result.put("verification",aBool);
        return result;
    }

    /**
     * 保存新手机号
     * @param param
     * @param token
     */
    @PostMapping("phone")
    public void updatePhone(@RequestBody Map<String,String> param,
                            @RequestHeader("Authorization") String token){
        String newPhone = param.get("phone");
        Boolean bool = this.myCenterService.updatePhone(token,newPhone);

    }
}
