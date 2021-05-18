package com.tanhua.dubbo.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.tanhua.dubbo.api.RecommendUserApi;
import com.tanhua.dubbo.api.UserLikeApi;
import com.tanhua.dubbo.pojo.RecommendUser;
import com.tanhua.dubbo.vo.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@DubboService(version = "1.0.0")
public class RecommendUserApiImpl implements RecommendUserApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserLikeApi userLikeApi;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public RecommendUser queryWithMaxScore(Long userId) {
        //条件：toUserId = {userId} 按照得分倒序排序，获取一条数据即可
        Query query = Query.query(Criteria.where("toUserId").is(userId))
                .with(Sort.by(Sort.Order.desc("score")))
                .limit(1);
        return this.mongoTemplate.findOne(query, RecommendUser.class);
    }

    @Override
    public PageInfo<RecommendUser> queryPageInfo(Long userId, Integer pageNum, Integer pageSize) {
        //设置分页参数
        PageRequest pageRequest = PageRequest.of(pageNum - 1, pageSize,
                Sort.by(Sort.Order.desc("score")));
        Query query = Query.query(Criteria.where("toUserId").is(userId)).with(pageRequest);

        List<RecommendUser> recommendUserList = this.mongoTemplate.find(query, RecommendUser.class);

        //TODO：列表查询时没有排除今日佳人，同学们自行实现:已完成************

        if (pageNum == 1 && recommendUserList.size() > 0) {
            recommendUserList.remove(0);
        }

        PageInfo<RecommendUser> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.setRecords(recommendUserList);
        //说明：暂不提供数据总条数

        return pageInfo;
    }

    /**
     * 查询推荐好友的缘分值
     *
     * @param userId   好友的id
     * @param toUserId 我的id
     * @return
     */
    @Override
    public Double queryScore(Long userId, Long toUserId) {
        //如果把这个人推荐给我的得分是多少？
        Query query = Query.query(Criteria.where("toUserId").is(toUserId)
                .and("userId").is(userId));
        RecommendUser recommendUser = this.mongoTemplate.findOne(query, RecommendUser.class);
        if (null != recommendUser) {
            return recommendUser.getScore();
        }

        //默认得分 TODO 可以做更优雅一些 --> 保存到redis中 已完成

        //从redis中取值
        String redisKey = "SCORE" + userId + "TO" + toUserId;
        String redisValue = this.redisTemplate.opsForValue().get(redisKey);
        Double score;
        //若值为空,则生成随机数
        if (StrUtil.isEmpty(redisValue)) {
            score = RandomUtil.randomDouble(80, 99);
            redisValue = StrUtil.toString(score);
            //存入redis中
            this.redisTemplate.opsForValue().set(redisKey, redisValue, 10, TimeUnit.HOURS);
            return score;
        }
        score = Convert.toDouble(redisValue);
        return score;

    }

    /**
     * 查询探花列表，查询时需要排除喜欢和不喜欢的用户
     *
     * @param userId
     * @param count
     * @return
     */
    @Override
    public List<RecommendUser> queryCardList(Long userId, Integer count) {
        Criteria criteria = Criteria.where("toUserId").is(userId);

        List<Long> userIdList = new ArrayList<>();
        //查询喜欢和不喜欢列表
        List<Long> likeList = this.userLikeApi.queryLikeList(userId);
        List<Long> notLikeList = this.userLikeApi.queryNotLikeList(userId);
        userIdList.addAll(likeList);
        userIdList.addAll(notLikeList);

        if (CollUtil.isNotEmpty(userIdList)) {
            criteria.andOperator(Criteria.where("userId").nin(userIdList));
        }

        Query query = Query.query(criteria).limit(count)
                .with(Sort.by(Sort.Order.desc("score")));
        return this.mongoTemplate.find(query, RecommendUser.class);
    }
}