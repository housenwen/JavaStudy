package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.common.exception.CustException;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.dtos.LoginDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserLoginService;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/14 9:25
 **/
@Service
public class ApUserLoginServiceImpl implements ApUserLoginService {

    @Autowired
    ApUserMapper apUserMapper;

    @Override
    public ResponseResult login(LoginDto dto) {
        // 1. 校验参数  (设备id        手机号 + 密码)
        Integer equipmentId = dto.getEquipmentId();
        String phone = dto.getPhone();
        String password = dto.getPassword();
        if(equipmentId==null && (StringUtils.isBlank(phone)||StringUtils.isBlank(password)) ){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2. 优先使用 手机号 + 密码
        if(StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(phone)){
            // 2.1  基于手机号 查询用户
            ApUser apUser = apUserMapper.selectOne(Wrappers.<ApUser>lambdaQuery().eq(ApUser::getPhone, phone));
            if(apUser==null){
                CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
            }
            // 2.2  检查用户状态
            // 2.3  对比密码
            String inputPwd = DigestUtils.md5DigestAsHex((password + apUser.getSalt()).getBytes());
            if(!inputPwd.equals(apUser.getPassword())){
                CustException.cust(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
            // 2.4  颁发token header  payload  signature
            String token = AppJwtUtil.getToken(Long.valueOf(apUser.getId()));

            Map map = new HashMap<>();
            map.put("token",token);
            apUser.setSalt("");
            apUser.setPassword("");
            map.put("user",apUser);
            return ResponseResult.okResult(map);
        }
        if(equipmentId != null){
            // 3. 使用设备id 登录
            String token = AppJwtUtil.getToken(0L);
            Map map = new HashMap<>();
            map.put("token",token);
            //   直接颁发token （userId = 0）
            return ResponseResult.okResult(map);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
    }
}
