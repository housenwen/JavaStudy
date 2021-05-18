package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.RecommendUser;
import com.tanhua.dubbo.vo.PageInfo;

import java.util.List;

public interface RecommendUserApi {

    /**
     * 查询一位得分最高的推荐用户
     *
     * @param userId
     * @return
     */
    RecommendUser queryWithMaxScore(Long userId);

    /**
     * 按照得分倒序
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<RecommendUser> queryPageInfo(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 查询推荐好友的缘分值
     *
     * @param userId   好友的id
     * @param toUserId 我的id
     * @return
     */
    Double queryScore(Long userId, Long toUserId);

    /**
     * 查询探花列表，查询时需要排除喜欢和不喜欢的用户
     *
     * @param userId
     * @param count
     * @return
     */
    List<RecommendUser> queryCardList(Long userId, Integer count);
}
