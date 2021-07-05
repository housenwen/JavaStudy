package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.AdUserMapper;
import com.heima.admin.service.AdUserService;
import com.heima.common.exception.CustException;
import com.heima.model.admin.dtos.AdUserDto;
import com.heima.model.admin.pojos.AdUser;
import com.heima.model.admin.vos.AdUserVO;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/3 15:21
 **/
@Service
public class AdUserServiceImpl extends ServiceImpl<AdUserMapper, AdUser> implements AdUserService {

    @Transactional(rollbackFor = Exception.class)  // 默认回滚的异常: RuntimeException
    @Override
    public ResponseResult login(AdUserDto dto) {
        // 1. 校验参数 (用户名 密码)
        if(StringUtils.isBlank(dto.getName()) || StringUtils.isBlank(dto.getPassword())){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"用户名或密码不能为空");
        }
        // 2. 根据用户名查询用户是否存在
        AdUser user = getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, dto.getName()));
        if(user == null){
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
        }
        // 3. 判断用户输入的密码和数据库中的密码是否一致
        String password = user.getPassword();
        String inputPsw = DigestUtils.md5DigestAsHex((dto.getPassword() + user.getSalt()).getBytes());
        if(!password.equals(inputPsw)){
            CustException.cust(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"密码不正确");
        }
        // 4. 判断用户状态是否正常
        if(user.getStatus().intValue() != 9){
            CustException.cust(AppHttpCodeEnum.LOGIN_STATUS_ERROR);
        }
        // 5. 修改最近的登录时间
        user.setLoginTime(new Date());
        updateById(user);
        // 6. 颁发token
        String token = AppJwtUtil.getToken(Long.valueOf(user.getId()));
        // 7. 封装结果返回
        Map map = new HashMap<>();
        map.put("token",token);
        AdUserVO userVO = new AdUserVO();
        BeanUtils.copyProperties(user,userVO);
        map.put("user",userVO);

        return ResponseResult.okResult(map);
    }
}
