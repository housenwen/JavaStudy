package com.heima.wemedia.service.impl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.exception.CustException;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.dtos.WmUserDto;
import com.heima.model.wemedia.pojos.WmUser;
import com.heima.utils.common.AppJwtUtil;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {

    @Transactional
    @Override
    public ResponseResult login(WmUserDto dto) {
        // 1. 校验用户名密码
        if(StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getPassword())){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"用户名或密码不能为空");
        }
        // 2. 根据用户名查询用户
        WmUser wmUser = getOne(Wrappers.<WmUser>lambdaQuery().eq(WmUser::getName, dto.getName()));
        if (wmUser == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"自媒体用户不存在");
        }

        // 3. 判断用户状态
        if(wmUser.getStatus().intValue() != 9 ){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"该用户状态异常");
        }
        // 4. 对比密码
        String inputPwd = DigestUtils.md5DigestAsHex((dto.getPassword() + wmUser.getSalt()).getBytes());
        String oldPwd = wmUser.getPassword();
        if(!inputPwd.equals(oldPwd)){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"用户名密码错误");
        }
        // 5. 颁发token
        String token = AppJwtUtil.getToken(Long.valueOf(wmUser.getId()));
        // 6. 修改登录时间
        wmUser.setLoginTime(new Date());
        updateById(wmUser);
        Map map = new HashMap<>();
        map.put("token",token);
        wmUser.setSalt("");
        wmUser.setPassword("");
        map.put("user",wmUser);
        return ResponseResult.okResult(map); // token  user
    }
}