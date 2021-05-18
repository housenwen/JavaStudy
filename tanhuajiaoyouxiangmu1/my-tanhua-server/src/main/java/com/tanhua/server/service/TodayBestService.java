package com.tanhua.server.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.tanhua.common.utils.UserThreadLocal;
import com.tanhua.dubbo.api.RecommendUserApi;
import com.tanhua.dubbo.api.UserInfoApi;
import com.tanhua.dubbo.pojo.RecommendUser;
import com.tanhua.dubbo.pojo.UserInfo;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.RecommendUserQueryParam;
import com.tanhua.server.vo.TodayBest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TodayBestService {

    @DubboReference(version = "1.0.0")
    private RecommendUserApi recommendUserApi;

    //TODO 可以进一步的优化，将默认配置写入到nacos中
    @Value("${tanhua.sso.default.user}")
    private Long defaultUser;

    @Value("${tanhua.sso.default.users}")
    private Long[] defaultUsers;

    @Autowired
    private UserService userService;

    @DubboReference(version = "1.0.0")
    private UserInfoApi userInfoApi;

    public TodayBest queryTodayBest() {
        //校验token是否有效
        Long userId = UserThreadLocal.get();

        //通过远程的服务查询今日佳人
        RecommendUser recommendUser = this.recommendUserApi.queryWithMaxScore(userId);
        if (recommendUser == null) {
            //给出一个默认的推荐用户
            recommendUser = new RecommendUser();
            recommendUser.setUserId(defaultUser);
            recommendUser.setToUserId(userId);
            //随机计算得分
            recommendUser.setScore(RandomUtil.randomDouble(80, 99));
        }

        TodayBest todayBest = new TodayBest();
        todayBest.setFateValue(recommendUser.getScore().longValue()); //缘分值

        //查询用户信息
        UserInfo userInfo = this.userInfoApi.queryByUserId(recommendUser.getUserId());
        BeanUtil.copyProperties(userInfo, todayBest, "id");
        todayBest.setId(userInfo.getUserId());
        // 性别，将枚举的name转成小写
        todayBest.setGender(userInfo.getSex().name().toLowerCase());

        return todayBest;
    }

    public PageResult queryRecommendList(RecommendUserQueryParam queryParam) {
        //校验token是否有效
        Long userId = UserThreadLocal.get();

        PageResult pageResult = new PageResult();
        pageResult.setPage(queryParam.getPage());
        pageResult.setPagesize(queryParam.getPagesize());

        //通过dubbo服务查询推荐列表
        PageInfo<RecommendUser> pageInfo = this.recommendUserApi.queryPageInfo(userId, queryParam.getPage(), queryParam.getPagesize());
        List<RecommendUser> recommendUserList = pageInfo.getRecords();
        if (CollUtil.isEmpty(recommendUserList)) {
            recommendUserList = new ArrayList<>();
            //给出默认的推荐列表
            for (Long uid : defaultUsers) {
                RecommendUser recommendUser = new RecommendUser();
                recommendUser.setUserId(uid);
                recommendUser.setToUserId(userId);
                recommendUser.setScore(RandomUtil.randomDouble(80, 99));
                recommendUserList.add(recommendUser);
            }
        }

        List<TodayBest> todayBestList = new ArrayList<>();

        //TODO 需要按照条件进行过滤用户，但是需要注意，下面的数据填充的循环要改成UserInfo为外层循环
        //TODO 一定注意推荐数据的顺序，要通过排序方式返回
        //TODO 参考： Collections.sort(todayBestList, (o1, o2) -> Convert.toInt(o2.getFateValue() - o1.getFateValue()));

        //获取到推荐的用户id列表
        List<Object> userIdList = CollUtil.getFieldValues(recommendUserList, "userId");
        //查询用户信息
        List<UserInfo> userInfoList = this.userInfoApi.queryByUserIdList(userIdList);

        for (RecommendUser recommendUser : recommendUserList) {
            TodayBest todayBest = new TodayBest();
            todayBest.setFateValue(recommendUser.getScore().longValue());
            todayBest.setId(recommendUser.getUserId());

            for (UserInfo userInfo : userInfoList) {
                if (ObjectUtil.equal(userInfo.getUserId(), recommendUser.getUserId())) {
                    BeanUtil.copyProperties(userInfo, todayBest, "id");
                    // 性别，将枚举的name转成小写
                    todayBest.setGender(userInfo.getSex().name().toLowerCase());
                    break;
                }
            }

            todayBestList.add(todayBest);
        }

        pageResult.setItems(todayBestList);
        return pageResult;
    }
}
