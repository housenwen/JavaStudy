package com.heima.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.media.pojos.WmUser;
import com.heima.utils.common.AppJwtUtil;
import com.heima.utils.common.MD5Utils;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;

@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {

    @Override
    public ResponseResult login(WmUser user) {
        if (StringUtils.isEmpty(user.getName()) || StringUtils.isEmpty(user.getPassword())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_REQUIRE, "用户名或密码不能为空");
        }
        Wrapper wrapper = new QueryWrapper<WmUser>();
        ((QueryWrapper) wrapper).eq("name", user.getName());
        List<WmUser> list = list(wrapper);
        if (list != null && list.size() == 1) {
            WmUser wmUser = list.get(0);
            String pswd = DigestUtils.md5DigestAsHex((user.getPassword() + wmUser.getSalt()).getBytes());
            if (wmUser.getPassword().equals(pswd)) {
                Map<String, Object> map = Maps.newHashMap();
                wmUser.setPassword("");
                wmUser.setSalt("");
                map.put("token", AppJwtUtil.getToken(wmUser.getId().longValue()));
                map.put("user", wmUser);
                return ResponseResult.okResult(map);
            } else {
                return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
            }
        } else {
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST, "用户不存在");
        }
    }
}