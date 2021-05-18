package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.dubbo.api.RecommendUserApi;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.dubbo.api.UserApi;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.api.UserLikeApi;
import com.tanhua.dubbo.enums.SexEnum;
import com.tanhua.dubbo.pojo.BlackList;
import com.tanhua.dubbo.pojo.Settings;
import com.tanhua.dubbo.pojo.User;
import com.tanhua.dubbo.pojo.UserInfo;
import com.tanhua.dubbo.pojo.UserLike;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.server.vo.CountsVo;
import com.tanhua.server.vo.PageResult;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.server.vo.BlackListVo;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.SettingsVo;
import com.tanhua.server.vo.UserInfoVo;
import com.tanhua.server.vo.VisitorsAllVo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyCenterService {

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;

    @DubboReference(version = "1.0.0")
    private UserLikeApi userLikeApi;
    @DubboReference(version = "1.0.0")
    private RecommendUserApi recommendUserApi;
    @Autowired
    private IMService imService;
    @Autowired
    private QuanZiService quanZiService;

    @DubboReference(version = "1.0.0")
    private UserApi userApi;
    @Autowired
    private QuestionService questionService;

    @Autowired
    private TanHuaService tanHuaService;

    @Autowired
    private SettingsService settingsService;

    @Autowired
    private BlackListService blackListService;

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    public UserInfoVo queryUserInfoByUserId(Long userId) {
        if (userId == null) {
            //如果传入的userId为空就说明是查询自己的信息
            userId = UserThreadLocal.get();
        }

        UserInfo userInfo = this.userInfoApi.queryByUserId(userId);
        UserInfoVo userInfoVo = BeanUtil.toBeanIgnoreError(userInfo, UserInfoVo.class);
        userInfoVo.setGender(userInfo.getSex().name().toLowerCase());
        userInfoVo.setMarriage(StrUtil.equals("已婚", userInfo.getMarriage()) ? 1 : 0);
        return userInfoVo;
    }

    /**
     * 是否喜欢
     *
     * @param userId
     * @return
     */
    public Boolean isLike(Long userId) {
        return this.userLikeApi.isLike(UserThreadLocal.get(), userId);
    }

    /**
     * 互相喜欢，喜欢，粉丝 - 统计
     *
     * @return
     */
    public CountsVo queryCounts() {
        Long userId = UserThreadLocal.get();
        CountsVo countsVo = new CountsVo();
        countsVo.setEachLoveCount(this.userLikeApi.queryMutualLikeCount(userId));
        countsVo.setLoveCount(this.userLikeApi.queryLikeCount(userId));
        countsVo.setFanCount(this.userLikeApi.queryFanCount(userId));
        return countsVo;
    }

    /**
     * 喜欢粉丝
     *
     * @param
     */
    public void likeFan(Long likeUserId) {
        //喜欢用户，如果用户是相互喜欢的话就会成为好友
        Long userId = UserThreadLocal.get();
        this.userLikeApi.likeUser(userId, likeUserId);
        //添加好友
        this.imService.addContacts(likeUserId);
    }

    /**
     * 取消喜欢
     *
     * @param
     * @return
     */
    public void disLike(Long dislikeUserId) {
        Long userId = UserThreadLocal.get();
        //先判断是否互相喜欢,互相喜欢则删除好友
        Boolean mutualLike = this.userLikeApi.isMutualLike(userId, dislikeUserId);
        //改为不喜欢
        this.userLikeApi.notLikeUser(userId, dislikeUserId);
        if (mutualLike) {
            this.imService.removeContacts(dislikeUserId);
        }
    }

    /**
     * 互相喜欢、喜欢、粉丝、谁看过我 - 翻页列表
     *
     * @param type
     * @param page
     * @param pageSize
     * @param nickName
     * @return
     */
    public PageResult queryTypeList(Integer type, Integer page, Integer pageSize, String nickName) {
        Long userId = UserThreadLocal.get();
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pageSize);
        List<Object> userIdList = null;
        //1 互相关注 2 我关注 3 粉丝 4 谁看过我
        switch (type) {
            case 1: {
                PageInfo<UserLike> pageInfo = this.userLikeApi.queryMyMutualLikeList(userId, page, pageSize);
                userIdList = CollUtil.getFieldValues(pageInfo.getRecords(), "userId");
                break;
            }
            case 2: {
                PageInfo<UserLike> pageInfo = this.userLikeApi.queryMyLikeList(userId, page, pageSize);
                userIdList = CollUtil.getFieldValues(pageInfo.getRecords(), "likeUserId");
                break;
            }
            case 3: {
                PageInfo<UserLike> pageInfo = this.userLikeApi.queryMyFanList(userId, page, pageSize);
                userIdList = CollUtil.getFieldValues(pageInfo.getRecords(), "userId");
                break;
            }
            case 4: {
                return this.quanZiService.queryAllVisitors(page, pageSize);
            }
            default:
                return pageResult;
        }

        if (CollUtil.isEmpty(userIdList)) {
            return pageResult;
        }
        Map<Long, UserInfo> userIdMap = this.userInfoApi
                .queryMapByUserIdList(userIdList);
        List<VisitorsAllVo> list = new ArrayList<>();
        for (Object o : userIdList) {
            Long queryUserId = Convert.toLong(o);
            UserInfo userInfo = userIdMap.get(queryUserId);
            VisitorsAllVo visitorsAllVo = BeanUtil.toBeanIgnoreError(userInfo, VisitorsAllVo.class);
            visitorsAllVo.setMarriage(userInfo.getMarriage() == "已婚" ? 1 : 0);
            visitorsAllVo.setGender(userInfo.getSex().name().toLowerCase());
            visitorsAllVo.setAlreadyLove(this.userLikeApi.isLike(userId, queryUserId));
            visitorsAllVo.setMatchRate(Convert.toInt(this.recommendUserApi.queryScore(userId, queryUserId)));
            list.add(visitorsAllVo);
        }
        pageResult.setItems(list);
        return pageResult;
    }

    /**
     * 从我的中心里更新个人信息
     *
     * @return
     */
    public Boolean updateUserInfo(UserInfoVo userInfoVo) {
        Long userId = UserThreadLocal.get();
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setAge(Integer.valueOf(userInfoVo.getAge()));
        userInfo.setSex(StrUtil.equalsIgnoreCase(userInfoVo.getGender(),"man")?SexEnum.MAN:SexEnum.WOMAN);
        userInfo.setBirthday(userInfoVo.getBirthday());
        userInfo.setCity(userInfoVo.getCity());
        userInfo.setEdu(userInfoVo.getEducation());
        userInfo.setIncome(userInfoVo.getIncome());
        userInfo.setIndustry(userInfoVo.getProfession());
        userInfo.setMarriage(userInfoVo.getMarriage()==1?"已婚":"未婚");

        return this.userInfoApi.update(userInfo);
    }

    /**
     * 查询配置
     * @return
     */
    public SettingsVo querySettings() {
        SettingsVo settingsVo = new SettingsVo();
        Long userId = UserThreadLocal.get();
        User user = this.userApi.queryById(userId);
        settingsVo.setId(userId);
        settingsVo.setPhone(user.getMobile());
        //查询用户的配置数据
        Settings settings = this.settingsService.querySettings(userId);
        if (ObjectUtil.isNotEmpty(settings)){
            settingsVo.setGonggaoNotification(settings.getGonggaoNotification());
            settingsVo.setLikeNotification(settings.getLikeNotification());
            settingsVo.setPinglunNotification(settings.getPinglunNotification());
        }
        //查询陌生人问题
        settingsVo.setStrangerQuestion(this.tanHuaService.queryQuestion(userId));
        return settingsVo;
    }

    /**
     * 保存陌生人问题
     * @param content
     */
    public void saveQuestions(String content) {
        Long userId = UserThreadLocal.get();
        this.questionService.save(userId,content);
    }

    /**
     * 查询黑名单
     * @param page
     * @param pagesize
     * @return
     */
    public PageResult queryBlacklist(Integer page, Integer pagesize) {
        Long userId = UserThreadLocal.get();
        PageInfo<BlackList> pageInfo = this.blackListService.queryBlacklist(userId, page, pagesize);
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setPagesize(pagesize);
        List<BlackList> records = pageInfo.getRecords();
        if (CollUtil.isEmpty(records)){
            return pageResult;
        }
        List<Object> userIds = CollUtil.getFieldValues(records, "blackUserId");
        List<UserInfo> userInfoList = this.userInfoApi.queryByUserIdList(userIds);
        List<BlackListVo> blackListVos =new ArrayList<>();
        for (UserInfo userInfo : userInfoList) {
            BlackListVo blackListVo = new BlackListVo();
            blackListVo.setAge(userInfo.getAge());
            blackListVo.setAvatar(userInfo.getLogo());
            blackListVo.setGender(userInfo.getSex().name().toLowerCase());
            blackListVo.setId(userInfo.getUserId());
            blackListVo.setNickname(userInfo.getNickName());
            blackListVos.add(blackListVo);
        }
        pageResult.setItems(blackListVos);

        return pageResult;
    }

    /**
     * 删除黑名单
     * @param userId
     */
    public void delBlacklist(Long userId) {
        Long userId1 = UserThreadLocal.get();
        this.blackListService.delBlacklist(userId1,userId);

    }

    /**
     * 更新用户的setting设置
     *
     * @param likeNotification
     * @param pinglunNotification
     * @param gonggaoNotification
     */
    public void updateNotification(Boolean likeNotification, Boolean pinglunNotification, Boolean gonggaoNotification) {
        Long userId = UserThreadLocal.get();
        this.settingsService.updateNotification(userId,likeNotification,pinglunNotification,gonggaoNotification);
    }

}
