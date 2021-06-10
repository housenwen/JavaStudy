package com.heima.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.admin.mapper.UserMapper;
import com.heima.admin.service.UserService;
import com.heima.model.admin.dto.AdUserDto;
import com.heima.model.admin.pojo.AdUser;
import com.heima.model.admin.vo.AdUserVo;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.utils.common.AppJwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/5/23 15:27
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, AdUser> implements UserService {
    @Override
    public ResponseResult login(AdUserDto dto) {
        // 1. 检查参数  用户名 和 密码都得存在
        if(StringUtils.isBlank(dto.getName())||StringUtils.isBlank(dto.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"用户名或密码不能为空");
        }
        // 2. 根据用户名查询user对象
        AdUser user = getOne(Wrappers.<AdUser>lambdaQuery().eq(AdUser::getName, dto.getName()));
        if(user == null){
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST,"用户不存在");
        }
        // 3. 检查密码是否正确                 MD5         (  前台传的密码   +   数据库中的盐  )
        String inputPsw = DigestUtils.md5DigestAsHex((dto.getPassword() + user.getSalt()).getBytes());
        if(!inputPsw.equals(user.getPassword())){
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR,"用户密码错误");
        }
        // 4. 检查user status字段为有效状态
        if(user.getStatus() != 9){
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID,"该用户状态异常，请联系管理员");
        }
        // 5. 设置最近的登录时间
        user.setLoginTime(new Date());
        updateById(user);
        // 6. 颁发token   返回user信息     data:
        String token = AppJwtUtil.getToken(user.getId());
        Map result = new HashMap();
        result.put("token",token);
        AdUserVo userVo = new AdUserVo();
        BeanUtils.copyProperties(user,userVo);
        result.put("user",userVo);
        return ResponseResult.okResult(result);
    }
}
