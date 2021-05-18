package com.tanhua.dubbo.api.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.tanhua.dubbo.api.VisitorsApi;
import com.tanhua.dubbo.pojo.Visitors;
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

import java.util.List;

@DubboService(version = "1.0.0")
public class VisitorsApiImpl implements VisitorsApi {


    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String VISITOR_REDIS_KEY = "VISITOR_USER";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存访客数据
     *
     * @param userId        我的id
     * @param visitorUserId 访客id
     * @param from          来源
     * @return
     */
    @Override
    public String saveVisitor(Long userId, Long visitorUserId, String from) {
        //排除自己
        if (userId.equals(visitorUserId)) {
            return null;
        }
        //查询该用户在今日是否已经记录过，如果记录过就不再记录
        String today = DateUtil.today();
        Long minDate = DateUtil.parseDateTime(today + " 00:00:00").getTime();
        Long maxDate = DateUtil.parseDateTime(today + " 23:59:59").getTime();

        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("visitorUserId").is(visitorUserId)
                .andOperator(Criteria.where("date").gte(minDate),
                        Criteria.where("date").lte(maxDate)
                ));

        Visitors visitors = this.mongoTemplate.findOne(query, Visitors.class);
        if (null != visitors) {
            //已经来过了，更新时间
            Query updateQuery = Query.query(Criteria.where("id").is(visitors.getId()));
            //更新成当前的时间戳
            Update update = Update.update("date", System.currentTimeMillis());
            this.mongoTemplate.updateFirst(updateQuery, update, Visitors.class);

            return visitors.getId().toString();
        }

        //记录新访客
        visitors = new Visitors();
        visitors.setId(ObjectId.get());
        visitors.setDate(System.currentTimeMillis());
        visitors.setUserId(userId);
        visitors.setVisitorUserId(visitorUserId);
        visitors.setFrom(from);

        this.mongoTemplate.save(visitors);
        return visitors.getId().toString();
    }

    /**
     * 查询我的访客数据，存在2种情况：
     * 1. 我没有看过我的访客数据，返回前5个访客信息
     * 2. 之前看过我的访客，从上一次查看的时间点往后查询5个访客数据
     *
     * @param userId
     * @return
     */
    @Override
    public List<Visitors> queryMyVisitor(Long userId) {
        Criteria criteria = Criteria.where("userId").is(userId);
        //TODO 在查询具体访客列表中记录时间到redis中,已经完成
        //查询该用户上一次查询列表的时间
        String hashKey = Convert.toStr(userId);
        //this.redisTemplate.opsForHash().put(VISITOR_REDIS_KEY, hashKey,System.currentTimeMillis());
        Object redisValue = this.redisTemplate.opsForHash().get(VISITOR_REDIS_KEY, hashKey);
        if (null != redisValue) {
            //在此时间之后查询
            criteria.and("date").gte(Convert.toLong(redisValue));
        }

        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Order.desc("date")));
        Query query = Query.query(criteria).with(pageRequest);
        return this.mongoTemplate.find(query, Visitors.class);
    }

    @Override
    public List<Visitors> queryMyAllVisitor(Long userId, Integer pageSize, Integer page) {
//        记录点击这一页的的时间
        String hashKey = Convert.toStr(userId);
        String s = Convert.toStr(System.currentTimeMillis());
        this.redisTemplate.opsForHash().put(VISITOR_REDIS_KEY, hashKey, s);
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
                Sort.by(Sort.Order.desc("date")));
        Query query = Query.query(Criteria.where("userId").is(userId)).with(pageRequest);
        List<Visitors> visitors = mongoTemplate.find(query, Visitors.class);
        return visitors;
    }
}
