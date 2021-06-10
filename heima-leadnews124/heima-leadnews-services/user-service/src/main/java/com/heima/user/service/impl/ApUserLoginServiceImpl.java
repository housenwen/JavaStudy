package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dto.LoginDto;
import com.heima.model.user.pojo.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserLoginService;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.crypto.Digest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/6/2 14:11
 **/
@Service
public class ApUserLoginServiceImpl implements ApUserLoginService {
    @Autowired
    ApUserMapper apUserMapper;

    @Override
    public ResponseResult login(LoginDto dto) {
        // 1. 检查参数  设备ID   手机号+密码
        if(dto.getEquipmentId()==null &&
                (StringUtils.isBlank(dto.getPhone())
                ||StringUtils.isBlank(dto.getPassword()))){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2. 如果包含手机号和密码
        if(StringUtils.isNotBlank(dto.getPhone())&&StringUtils.isNotBlank(dto.getPassword())){
            // 2.1   使用手机号查询ap_user是否存在
            ApUser apUser = apUserMapper.selectOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, dto.getPhone()));
            if(apUser == null){
                return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
            }
            // 2.2   存在对比密码是否正确
            String inputPwd = DigestUtils.md5DigestAsHex((dto.getPassword() + apUser.getSalt()).getBytes());
            if(!inputPwd.equals(apUser.getPassword())){
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"密码错误");
            }
            // 2.3   颁发token
            String token = AppJwtUtil.getToken(apUser.getId());
            Map map = new HashMap<>();
            map.put("token",token);
            apUser.setPassword("");
            apUser.setSalt("");
            map.put("user",apUser);
            return ResponseResult.okResult(map);
        }else {
            // 3. 代表使用设备ID 登录  直接颁发token
            String token = AppJwtUtil.getToken(0);
            Map map = new HashMap<>();
            map.put("token",token);
            return ResponseResult.okResult(map);
        }
    }
}
