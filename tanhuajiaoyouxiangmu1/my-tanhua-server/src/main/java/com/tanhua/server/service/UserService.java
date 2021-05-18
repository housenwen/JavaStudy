package com.tanhua.server.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.common.utils.JwtUtils;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.mapper.UserMapper;
import com.tanhua.dubbo.pojo.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserService {

    @Autowired
    private RSAService rsaService;

    @Autowired
    private UserMapper userMapper;



    /**
     * 通过公钥校验token，校验成功后返回用户id
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

    public Boolean updatePhone(Long userId, String newPhone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",userId);
        User user = new User();
        user.setMobile(newPhone);
        int update = this.userMapper.update(user, queryWrapper);

        return update==1;
    }
}
