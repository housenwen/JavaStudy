package com.tanhua.dubbo.api.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.mapper.UserInfoMapper;
import com.tanhua.dubbo.pojo.UserInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DubboService(version = "1.0.0")
public class UserInfoApiImpl implements UserInfoApi {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Boolean save(UserInfo userInfo) {
        userInfo.setId(null); //防止别人传入id，所以设置成null
        userInfo.setAge(DateUtil.ageOfNow(userInfo.getBirthday()));
        if (StrUtil.isEmpty(userInfo.getMarriage())) {
            userInfo.setMarriage("未婚");
        }
        if (StrUtil.isEmpty(userInfo.getTags())) {
            userInfo.setTags("单身,本科,年龄相仿");
        }
        if (StrUtil.isEmpty(userInfo.getIndustry())) {
            userInfo.setIndustry("程序员");
        }
        if (StrUtil.isEmpty(userInfo.getIncome())) {
            userInfo.setIncome("40k");
        }
        if (StrUtil.isEmpty(userInfo.getEdu())) {
            userInfo.setEdu("本科");
        }

        return this.userInfoMapper.insert(userInfo) == 1;
    }

    @Override
    public Boolean update(UserInfo userInfo) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userInfo.getUserId());
        return this.userInfoMapper.update(userInfo, queryWrapper) == 1;
    }

    @Override
    public UserInfo queryByUserId(Long userId) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        return this.userInfoMapper.selectOne(queryWrapper);
    }

    /**
     * 根据用户id列表查询用户信息列表
     *
     * @param userIdList
     * @return
     */
    @Override
    public List<UserInfo> queryByUserIdList(List<Object> userIdList) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", userIdList);
        return this.userInfoMapper.selectList(queryWrapper);
    }

    @Override
    public Map<Long, UserInfo> queryMapByUserIdList(List<Object> userIdList) {
        //传统写法
        // List<UserInfo> userInfoList = this.queryByUserIdList(userIdList);
        // Map<Long, UserInfo> map = new HashMap<>();
        // for (UserInfo userInfo : userInfoList) {
        //     map.put(userInfo.getUserId(), userInfo);
        // }
        // return map;

        //新的写法
        return this.queryByUserIdList(userIdList).stream()
                .collect(Collectors.toMap(UserInfo::getUserId, userInfo -> userInfo));
    }

    @Override
    public List<UserInfo> queryLikeUserName(List<Object> userIdList, String userName) {
        QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id", userIdList);
        queryWrapper.like("nick_name", userName);
        return this.userInfoMapper.selectList(queryWrapper);
    }
}
