package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.common.vo.ErrorResult;
import com.tanhua.dubbo.api.*;
import com.tanhua.dubbo.enums.HuanXinMessageType;
import com.tanhua.dubbo.pojo.Question;
import com.tanhua.dubbo.pojo.RecommendUser;
import com.tanhua.dubbo.pojo.UserInfo;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.dubbo.vo.UserLocationVo;
import com.tanhua.server.vo.NearUserVo;
import com.tanhua.server.vo.TodayBest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TanHuaService {

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;

    @DubboReference(version = "1.0.0")
    private RecommendUserApi recommendUserApi;

    @DubboReference(version = "1.0.0")
    private QuestionApi questionApi;

    @DubboReference(version = "1.0.0")
    private HuanXinApi huanXinApi;

    @DubboReference(version = "1.0.0")
    private VisitorsApi visitorsApi;

    @DubboReference(version = "1.0.0")
    private UserLocationApi userLocationApi;

    @DubboReference(version = "1.0.0")
    private UserLikeApi userLikeApi;

    @Value("${tanhua.sso.default.users}")
    private String[] defaultUsers;

    @Autowired
    private IMService imService;

    public TodayBest queryUserInfo(Long userId) {
        //查询用户信息
        UserInfo userInfo = this.userInfoApi.queryByUserId(userId);
        TodayBest todayBest = BeanUtil.toBeanIgnoreError(userInfo, TodayBest.class);
        // 性别，将枚举的name转成小写
        todayBest.setGender(userInfo.getSex().name().toLowerCase());
        //缘份值
        todayBest.setFateValue(this.recommendUserApi
                .queryScore(userId, UserThreadLocal.get()).longValue());

        //记录访客
        this.visitorsApi.saveVisitor(userId, UserThreadLocal.get(), "个人主页");

        return todayBest;
    }

    public String queryQuestion(Long userId) {
        Question question = this.questionApi.queryQuestion(userId);
        if (question != null) {
            return question.getTxt();
        }
        //默认问题
        return "你是GG还是MM？";
    }

    /**
     * 回复陌生人问题
     *
     * @param userId 对方的id
     * @param reply
     */
    public Object replyQuestion(Long userId, String reply) {
        //{"userId":1,"huanXinId":"HX_1","nickname":"黑马小妹","strangerQuestion":"你喜欢去看蔚蓝的大海还是去爬巍峨的高山？","reply":"我喜欢秋天的落叶，夏天的泉水，冬天的雪地，只要有你一切皆可~"}
        Long myId = UserThreadLocal.get();

        UserInfo userInfo = this.userInfoApi.queryByUserId(myId);

        Map<String, Object> msg = new HashMap<>();
        msg.put("userId", myId);
        msg.put("huanXinId", "HX_" + myId);
        msg.put("nickname", userInfo.getNickName());
        msg.put("strangerQuestion", this.queryQuestion(userId));
        msg.put("reply", reply);

        Boolean result = this.huanXinApi.sendMsgFromAdmin("HX_" + userId,
                HuanXinMessageType.TXT, JSONUtil.toJsonStr(msg));

        if (!result) {
            // 发送失败
            return ErrorResult.builder()
                    .errCode("5001")
                    .errMessage("发送消息失败！")
                    .build();
        }

        return null;
    }

    public List<NearUserVo> queryNearUser(String gender, String distance) {

        Long userId = UserThreadLocal.get();

        //查询自己的坐标
        UserLocationVo userLocationVo = this.userLocationApi.queryByUserId(userId);
        if (null == userLocationVo) {
            return Collections.emptyList();
        }

        //查询附近的人
        PageInfo<UserLocationVo> pageInfo = this.userLocationApi.queryUserFromLocation(userLocationVo.getLongitude(),
                userLocationVo.getLatitude(), Convert.toDouble(distance), 1, 20);
        List<UserLocationVo> userLocationVoListTemp = pageInfo.getRecords();
        if (CollUtil.isEmpty(userLocationVoListTemp)) {
            return Collections.emptyList();
        }
        Map<Long, UserInfo> userInfoMapTemp = this.userInfoApi.queryMapByUserIdList(CollUtil.getFieldValues(userLocationVoListTemp, "userId"));
        //TODO 对性别进行筛选 已完成
        // TODO: 排除自己 已完成
        //新建空集合用来保存符合条件的数据
        List<UserLocationVo> userLocationVoList = new ArrayList<>();
        for (UserLocationVo locationVo : userLocationVoListTemp) {
            //当性别与传入性别相等时,并且排除自己的id时放入集合
            if (userInfoMapTemp.get(locationVo.getUserId()).getSex().name().toLowerCase().equals(gender)
                    && (!userInfoMapTemp.get(locationVo.getUserId()).getUserId().equals(userId))) {
                userLocationVoList.add(locationVo);
            }
        }
        //对查询到饿数据进行判断,如果为空则返回空集合,否则mysql查询时会报错
        if (CollUtil.isEmpty(userLocationVoList)) {
            return Collections.emptyList();
        }
        Map<Long, UserInfo> userInfoMap = this.userInfoApi.queryMapByUserIdList(CollUtil.getFieldValues(userLocationVoList, "userId"));

        return userLocationVoList.stream()
                .map(locationVo -> BeanUtil.toBeanIgnoreError(userInfoMap.get(locationVo.getUserId()), NearUserVo.class))
                .collect(Collectors.toList());
    }

    /**
     * 查询推荐卡片列表，从推荐列表中随机选取10个用户
     *
     * @return
     */
    public List<TodayBest> queryCardsList() {
        Long userId = UserThreadLocal.get();
        List<RecommendUser> recommendUserList = this.recommendUserApi.queryCardList(userId, 30);
        if (CollUtil.isEmpty(recommendUserList)) {
            //默认推荐列表
            recommendUserList = new ArrayList<>();
            for (String defaultUser : defaultUsers) {
                RecommendUser recommendUser = new RecommendUser();
                recommendUser.setUserId(Convert.toLong(defaultUser));
                recommendUser.setToUserId(userId);
                recommendUser.setScore(RandomUtil.randomDouble(80, 99));
                recommendUserList.add(recommendUser);
            }
        }

        //随机抽取10个进行返回
        // List<RecommendUser> newRecommendUserList = new ArrayList<>();
        // for (int i = 0; i < 10; i++) {
        //     int index = RandomUtil.randomInt(0, recommendUserList.size());
        //     RecommendUser recommendUser = recommendUserList.get(index);
        //     newRecommendUserList.add(recommendUser);
        // }
        Collections.shuffle(recommendUserList); //打乱顺序
        List<RecommendUser> newRecommendUserList = ListUtil.sub(recommendUserList, 0, 10);

        //查询用户信息
        Map<Long, UserInfo> userInfoMap = this.userInfoApi.queryMapByUserIdList(CollUtil.getFieldValues(newRecommendUserList, "userId"));

        return newRecommendUserList.stream().map(recommendUser -> {
            TodayBest todayBest = new TodayBest();
            todayBest.setFateValue(recommendUser.getScore().longValue()); //缘分值
            //查询用户信息
            UserInfo userInfo = userInfoMap.get(recommendUser.getUserId());
            BeanUtil.copyProperties(userInfo, todayBest, "id");
            todayBest.setId(userInfo.getUserId());
            // 性别，将枚举的name转成小写
            todayBest.setGender(userInfo.getSex().name().toLowerCase());
            return todayBest;
        }).collect(Collectors.toList());
    }

    /**
     * 喜欢
     *
     * @param likeUserId
     */
    public void likeUser(Long likeUserId) {
        Long userId = UserThreadLocal.get();
        Boolean result = this.userLikeApi.likeUser(userId, likeUserId);
        if (result) {
            //判断是否是相互喜欢，如果是，就添加好友
            if (this.userLikeApi.isMutualLike(userId, likeUserId)) {
                this.imService.addContacts(likeUserId);
            }
        }
    }

    /**
     * 不喜欢
     *
     * @param likeUserId
     */
    public void notLikeUser(Long likeUserId) {
        Long userId = UserThreadLocal.get();
        this.userLikeApi.notLikeUser(userId, likeUserId);
    }
}
