package com.tanhua.sso.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyCenterService {

    @Autowired
    private UserService userService;

    @Autowired
    private SmsService smsService;

    @DubboReference(version = "1.0.0")
    private UserApi userApi;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;





    /**
     * 发送验证码
     * @param token
     */
    public Boolean sendVerificationCode(String token) {

        //校验token
        Long userId = this.userService.checkToken(token);
        if (ObjectUtil.isEmpty(userId)){
            return false;
        }
        User user = this.userApi.queryById(userId);
        ErrorResult errorResult = this.smsService.sendCheckCode(user.getMobile());
        return errorResult==null;
    }

    /**
     * 校验验证码
     * @param code
     * @param token
     * @return
     */
    public Boolean checkVerificationCode(String code, String token) {
        Long userId = this.userService.checkToken(token);
        if (userId==null){
            return false;
        }
        User user = this.userApi.queryById(userId);
        String redisKey = "CHECK_CODE_"+user.getMobile();
        String value = this.redisTemplate.opsForValue().get(redisKey);
        if (StrUtil.equals(code,value)){
            this.redisTemplate.delete(redisKey);
            return true;
        }
        return false;
    }

    /**
     * 更新手机号
     * @param token
     * @param newPhone
     * @return
     */
    public Boolean updatePhone(String token, String newPhone) {
        Long userId = this.userService.checkToken(token);
        return this.userApi.updateNewPhone(userId, newPhone);
    }
}
