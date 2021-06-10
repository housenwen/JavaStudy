package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dto.WmUserDto;
import com.heima.model.wemedia.pojo.WmUser;
import com.heima.utils.common.AppJwtUtil;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/5/25 9:19
 **/
@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {
    @Override
    public ResponseResult login(WmUserDto dto) {
        // 1. 检查用户名密码是否输入
        if(StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 2. 根据用户名查询对应用户
        WmUser user = this.getOne(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, dto.getName()));
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
        }
        // 3. 判断用户密码 和 输入密码是否一致
        String inputPwd = DigestUtils.md5DigestAsHex((dto.getPassword() + user.getSalt()).getBytes());
        if(!inputPwd.equals(user.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        // 4. 如果一致，颁发token
        String token = AppJwtUtil.getToken(user.getId());
        Map result = new HashMap<>();
        result.put("token",token);
        user.setPassword("");
        user.setSalt("");
        result.put("user",user);
        return ResponseResult.okResult(result);
    }
}
