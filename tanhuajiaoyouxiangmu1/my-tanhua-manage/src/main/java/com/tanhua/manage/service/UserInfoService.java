package com.tanhua.manage.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanhua.manage.mapper.UserInfoMapper;
import com.tanhua.manage.pojo.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

    public UserInfo queryUserInfo(Long userId) {
        return super.getOne(Wrappers.<UserInfo>lambdaQuery()
                .eq(UserInfo::getUserId, userId));
    }
}