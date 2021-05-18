package com.tanhua.manage.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tanhua.dubbo.api.UserLikeApi;
import com.tanhua.manage.enums.LogTypeEnum;
import com.tanhua.manage.enums.SexEnum;
import com.tanhua.manage.mapper.UserInfoMapper;
import com.tanhua.manage.mapper.UserMapper;
import com.tanhua.manage.pojo.Log;
import com.tanhua.manage.pojo.User;
import com.tanhua.manage.pojo.UserInfo;
import com.tanhua.manage.vo.Pager;
import com.tanhua.manage.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {
        @Autowired
        private UserInfoMapper userInfoMapper;

        @Autowired
        private UserMapper userMapper;

        @Autowired
        private LogService logService;

        @Autowired
        private UserFreezeService userFreezeService;

        /**
         * 根据条件获取用户分页数据
         *
         * @param page     当前页码
         * @param pageSize 页尺寸
         * @param id       用户id
         * @param nickname 昵称
         * @param city     居住城市
         * @return 用户分页数据
         */
        public Pager<UserVo> queryByPage(Integer page, Integer pageSize, Long id, String nickname, String city) {
                IPage<UserVo> pageData = this.userMapper.queryByPage(new Page<>(page, pageSize), id == null ? null : String.valueOf(id), nickname, city);

                //处理性别名称的问题
                pageData.getRecords().forEach(userVo -> {
                        userVo.setSex(SexEnum.getSexByValue(Integer.valueOf(userVo.getSex())));
                        //查询用户状态
                        userVo.setUserStatus(this.userFreezeService.getFreezeStatusByUserId(userVo.getId()) ? "2" : "1");
                });

                return new Pager<>(pageData);
        }



        @DubboReference(version = "1.0.0")
        private UserLikeApi userLikeApi;

        public UserVo queryUserInfo(Long userId) {
                UserVo userVo = new UserVo();

                User user = super.getById(userId);
                if (ObjectUtil.isEmpty(user)) {
                        return userVo;
                }

                UserInfo userInfo = this.userInfoMapper.selectOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserId, userId));
                if (ObjectUtil.isEmpty(userInfo)) {
                        return userVo;
                }

                //复制属性值
                BeanUtil.copyProperties(userInfo, userVo);
                BeanUtil.copyProperties(user, userVo);
                userVo.setNickname(userInfo.getNickName());

                //用户状态
                userVo.setUserStatus(userFreezeService.getFreezeStatusByUserId(userVo.getId()) ? "2" : "1");

                // 喜欢数、粉丝数、配对数
                Integer integer = userLikeApi.queryFanCount(user.getId());
                userVo.setCountBeLiked(Convert.toLong(integer));
                Integer integer1 = userLikeApi.queryLikeCount(user.getId());
                userVo.setCountLiked(Convert.toLong(integer1));
                userVo.setCountMatching(userLikeApi.queryEachLikeCount(user.getId()));


                // 最近活跃时间
                Log activeLog = this.logService.getOne(Wrappers.<Log>lambdaQuery()
                        .eq(Log::getUserId, userId)
                        .orderByDesc(Log::getCreated)
                        .last("LIMIT 1")
                );
                if (activeLog != null) {
                        userVo.setLastActiveTime(activeLog.getCreated().getTime());
                }

                // 最近登陆地点
                Log loginLog = this.logService.getOne(Wrappers.<Log>lambdaQuery()
                        .eq(Log::getUserId, userId)
                        .eq(Log::getType, LogTypeEnum.LOGIN.getValue())
                        .orderByDesc(Log::getCreated)
                        .last("LIMIT 1")
                );
                if (loginLog != null) {
                        userVo.setLastLoginLocation(loginLog.getPlace());
                }

                return userVo;
        }
}