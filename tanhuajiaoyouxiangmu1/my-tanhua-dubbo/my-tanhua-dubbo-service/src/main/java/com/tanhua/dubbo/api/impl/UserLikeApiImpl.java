package com.tanhua.dubbo.api.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import com.tanhua.dubbo.api.UserLikeApi;
import com.tanhua.dubbo.pojo.UserLike;
import com.tanhua.dubbo.vo.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@DubboService(version = "1.0.0")
public class UserLikeApiImpl implements UserLikeApi {

    public static final String LIKE_REDIS_KEY_PREFIX = "USER_LIKE_";

    public static final String NOT_LIKE_REDIS_KEY_PREFIX = "USER_NOT_LIKE_";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    @Override
    public Boolean likeUser(Long userId, Long likeUserId) {
        //校验是否已经喜欢过，如果已经喜欢就返回
        if (this.isLike(userId, likeUserId)) {
            return false;
        }

        this.saveMongoDB(userId, likeUserId, true);

        //保存到redis，使用的hash结构
        //1喜欢2， 大key: USER_LIKE_1；小key："2" ； 值："1"
        //1喜欢3， 大key: USER_LIKE_1；小key："3" ； 值："1"
        //1不喜欢4， 大key: USER_NOT_LIKE_1；小key："4" ； 值："1"

        String redisKey = this.getLikeRedisKey(userId);
        String hashKey = Convert.toStr(likeUserId);
        //存储到喜欢列表中
        this.redisTemplate.opsForHash().put(redisKey, hashKey, "1");

        //将该用户在不喜欢列表中删除
        redisKey = this.getNotLikeRedisKey(userId);
        this.redisTemplate.opsForHash().delete(redisKey, hashKey);

        return true;
    }

    private void saveMongoDB(Long userId, Long likeUserId, Boolean isLike) {
        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("likeUserId").is(likeUserId));
        UserLike userLike = this.mongoTemplate.findOne(query, UserLike.class);

        if (ObjectUtil.isEmpty(userLike)) {
            //存储到MongoDB
            userLike = new UserLike();
            userLike.setId(ObjectId.get());
            userLike.setUserId(userId);
            userLike.setLikeUserId(likeUserId);
            userLike.setCreated(System.currentTimeMillis());
            userLike.setUpdated(userLike.getCreated());
            userLike.setIsLike(isLike);
            this.mongoTemplate.save(userLike);
        } else {
            //更新字段
            Update update = Update.update("isLike", isLike)
                    .set("updated", System.currentTimeMillis());
            this.mongoTemplate.updateFirst(query, update, UserLike.class);
        }
    }

    private String getLikeRedisKey(Long userId) {
        return LIKE_REDIS_KEY_PREFIX + userId;
    }

    private String getNotLikeRedisKey(Long userId) {
        return NOT_LIKE_REDIS_KEY_PREFIX + userId;
    }

    /**
     * 不喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    @Override
    public Boolean notLikeUser(Long userId, Long likeUserId) {
        if (this.isNotLike(userId, likeUserId)) {
            return false;
        }

        this.saveMongoDB(userId, likeUserId, false);

        String redisKey = this.getNotLikeRedisKey(userId);
        String hashKey = Convert.toStr(likeUserId);
        //存储到不喜欢列表中
        this.redisTemplate.opsForHash().put(redisKey, hashKey, "1");

        //将该用户在喜欢列表中删除
        redisKey = this.getLikeRedisKey(userId);
        this.redisTemplate.opsForHash().delete(redisKey, hashKey);

        return true;
    }

    /**
     * 是否喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    @Override
    public Boolean isLike(Long userId, Long likeUserId) {
        String redisKey = this.getLikeRedisKey(userId);
        String hashKey = Convert.toStr(likeUserId);
        return this.redisTemplate.opsForHash().hasKey(redisKey, hashKey);
    }

    /**
     * 是否不喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    @Override
    public Boolean isNotLike(Long userId, Long likeUserId) {
        String redisKey = this.getNotLikeRedisKey(userId);
        String hashKey = Convert.toStr(likeUserId);
        return this.redisTemplate.opsForHash().hasKey(redisKey, hashKey);
    }

    /**
     * 是否相互喜欢
     *
     * @param userId
     * @param likeUserId
     * @return
     */
    @Override
    public Boolean isMutualLike(Long userId, Long likeUserId) {
        return this.isLike(userId, likeUserId) && this.isLike(likeUserId, userId);
    }

    /**
     * 查询喜欢列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> queryLikeList(Long userId) {
        return this.queryList(this.getLikeRedisKey(userId));
    }

    /**
     * 查询不喜欢列表
     *
     * @param userId
     * @return
     */
    @Override
    public List<Long> queryNotLikeList(Long userId) {
        return this.queryList(this.getNotLikeRedisKey(userId));
    }

    /**
     * 相互喜欢的数量
     *
     * @return
     */
    @Override
    public Integer queryMutualLikeCount(Long userId) {
        //思路：先查询我喜欢的列表，在列表中查看这些人是否也喜欢我
        //调用查询我喜欢的列表
        List<Long> myLikeIdList = this.queryLikeList(userId);
        //定义喜欢计数,初始为0,遍历集合调用是否喜欢统计是否互相喜欢,返回计数值
        Integer count = 0;
        for (Long likeId : myLikeIdList) {
            if (this.isLike(likeId, userId)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 喜欢数
     *
     * @return
     */
    @Override
    public Integer queryLikeCount(Long userId) {
        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("isLike").is(true));
        return Convert.toInt(this.mongoTemplate.count(query, UserLike.class));
    }

    @Override
    public Long queryEachLikeCount(Long userId) {
        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("isLike").is(true));
        return this.mongoTemplate.count(query, UserLike.class);
    }

    /**
     * 粉丝数
     *
     * @return
     */
    @Override
    public Integer queryFanCount(Long userId) {
        Query query = Query.query(Criteria.where("likeUserId").is(userId)
                .and("isLike").is(true));
        return Convert.toInt(this.mongoTemplate.count(query, UserLike.class));
    }

    private List<Long> queryList(String redisKey) {
        Set<Object> keys = this.redisTemplate.opsForHash().keys(redisKey);
        if (CollUtil.isEmpty(keys)) {
            return Collections.emptyList();
        }
        return keys.stream().map(o -> Convert.toLong(o))
                .collect(Collectors.toList());
    }

    /**
     * 查询相互喜欢列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserLike> queryMyMutualLikeList(Long userId, Integer page, Integer pageSize) {
        //思路：先从redis查询我喜欢的列表，找出喜欢我的人，再到MongoDB中查询
        List<Long> likeList = this.queryLikeList(userId);
        Query query = Query.query(Criteria.where("userId").in(likeList)
                .and("likeUserId").is(userId)
                .and("isLike").is(true));
        PageInfo<UserLike> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);
        pageInfo.setRecords(this.mongoTemplate.find(query, UserLike.class));
        return pageInfo;
    }

    /**
     * 查询我喜欢的列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserLike> queryMyLikeList(Long userId, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Order.desc("updated")));
        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("isLike").is(true)).with(pageRequest);

        List<UserLike> userLikeList = this.mongoTemplate.find(query, UserLike.class);
        PageInfo<UserLike> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);
        pageInfo.setRecords(userLikeList);
        return pageInfo;
    }

    /**
     * 查询粉丝列表
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @Override
    public PageInfo<UserLike> queryMyFanList(Long userId, Integer page, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Order.desc("updated")));
        Query query = Query.query(Criteria.where("likeUserId").is(userId)
                .and("isLike").is(true)).with(pageRequest);
        List<UserLike> userLikeList = this.mongoTemplate.find(query, UserLike.class);
        PageInfo<UserLike> pageInfo = new PageInfo<>();
        pageInfo.setRecords(userLikeList);
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);
        return pageInfo;
    }
}
