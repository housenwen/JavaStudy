package com.tanhua.manage.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanhua.manage.exception.BusinessException;
import com.tanhua.manage.mapper.AdminMapper;
import com.tanhua.manage.pojo.Admin;
import com.tanhua.manage.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AdminService {
    @Autowired
    private RSAService rsaService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String CACHE_KEY_CAP_PREFIX = "MANAGE_CAP_";

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private AdminMapper adminMapper;

    private static final String CACHE_KEY_TOKEN_PREFIX="MANAGE_TOKEN_";
    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 保存验证码信息
     */
    public void saveVerificationCode(String uuid, String code) {

        String key = CACHE_KEY_CAP_PREFIX + uuid;
        //将验证码保存到redis中，有效时间为5分钟
        this.redisTemplate.opsForValue().set(key, code, Duration.ofMinutes(10));
        System.out.println(this.redisTemplate.opsForValue().get(key));
    }

    /**
     * 登录
     */
    public String login(Admin admin, String uuid, String verificationCode){
        //校验验证码是否正确
        if(StrUtil.isEmpty(uuid)||StrUtil.isEmpty(verificationCode)){
            throw new BusinessException("验证码校验错误");
        }

        String redisCapKey = CACHE_KEY_CAP_PREFIX + uuid;
        System.out.println(redisCapKey);
        String code=this.redisTemplate.opsForValue().get(redisCapKey);
        System.out.println(code);
        if(StrUtil.isEmpty(code)||!verificationCode.equals(code)){
            throw new BusinessException("验证码校验错误");
        }
        this.redisTemplate.delete(redisCapKey);
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",admin.getUsername());
        Admin source = adminMapper.selectOne(queryWrapper);
        if(source==null){
            throw new BusinessException("用户不存在");
        }

        // 校验密码
        if(StrUtil.isEmpty(admin.getPassword())){
            throw new BusinessException("密码不能为空");
        }
        if(!source.getPassword().equals(SecureUtil.md5(admin.getPassword()))){
            throw new BusinessException("密码错误");
        }

        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", source.getUsername());
        claims.put("id", source.getId());

    /*    // 生成token
        String token = Jwts.builder()
                .setClaims(claims) //设置响应数据体
                .signWith(SignatureAlgorithm.HS256, secret) //设置加密方法和加密盐
                .compact();*/
        String token = JwtUtils.createToken(claims, rsaService.getPrivateKey(), 10);

        try {
            // 将token存储到redis中
            String redisTokenKey = CACHE_KEY_TOKEN_PREFIX + token;

            //将密码设置为null，不参与序列化
            admin.setPassword(null);

            String redisTokenValue = MAPPER.writeValueAsString(source);
            this.redisTemplate.opsForValue().set(redisTokenKey, redisTokenValue, Duration.ofDays(7));
            return token;
        } catch (Exception e) {
            log.error("存储token出错", e);
            return null;
        }
    }



    /*
    * 从token中读取用户信息
    * */
    /**
     * 根据token从redis中获取用户信息
     */
    public Admin queryUserByToken(String token) {
        try {
            String redisTokenKey = CACHE_KEY_TOKEN_PREFIX + token;
            String cacheData = this.redisTemplate.opsForValue().get(redisTokenKey);
            if (StrUtil.isEmpty(cacheData)) {
                return null;
            }
            // 刷新时间
            this.redisTemplate.expire(redisTokenKey, 7, TimeUnit.DAYS);
            return MAPPER.readValue(cacheData, Admin.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Admin getById(Long id) {
        Admin admin = adminMapper.selectById(id);
        return admin;
    }
}