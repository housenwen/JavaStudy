package com.tanhua.sso.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.common.utils.JwtUtils;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.dubbo.api.HuanXinApi;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @DubboReference(version = "1.0.0")
    private UserApi userApi;

    @DubboReference(version = "1.0.0")
    private HuanXinApi huanXinApi;

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private RSAService rsaService;


    public Object loginVerification(String phone, String code) {
        //校验验证码是否正确
        String redisKey = "CHECK_CODE_" + phone;
        String redisValue = this.redisTemplate.opsForValue().get(redisKey);
        if (!StrUtil.equals(code, redisValue)) {
            return ErrorResult.builder()
                    .errCode("5003")
                    .errMessage("验证码错误，请重新输入！").build();
        }
        System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
        //删除该验证码
        this.redisTemplate.delete(redisKey);

        //校验手机号是否存在，如果不存在，就需要注册新用户
        User user = this.userApi.queryByMobile(phone);
        boolean isNew = false;
        if (null == user) {
            isNew = true;
            //注册新用户
            Long userId = this.userApi.save(phone);
            user = new User();
            user.setId(userId);

            //注册用户到环信
            Boolean result = this.huanXinApi.register(userId);
            if (!result) {
                //注册失败
                log.error("注册环信失败， userId = " + userId);
            }
        }

        //生成token
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("id", user.getId());

        String token = JwtUtils.createToken(claims, this.rsaService.getPrivateKey(), 10);

        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("isNew", isNew);

        try {
            //发送消息通知其它系统
            Map<String, Object> msgMap = new HashMap<>();
            msgMap.put("userId", user.getId()); //用户id
            msgMap.put("date", System.currentTimeMillis()); //用户登录的时间
            msgMap.put("isNew", isNew); //是否是新用户

            this.rocketMQTemplate.convertAndSend("tanhua-sso-login", msgMap);
        } catch (Exception e) {
            log.error("发送登录成功的消息失败！ userId = " + user.getId(), e);
        }

        return result;
    }

    /**
     * 校验token，如果校验通过返回用户id，否则返回null
     *
     * @param token
     * @return
     */
    public Long checkToken(String token) {
        Map<String, Object> map = JwtUtils.checkToken(token, this.rsaService.getPublicKey());
        if (CollUtil.isNotEmpty(map)) {
            return Convert.toLong(map.get("id"));
        }
        return null;
    }

}
