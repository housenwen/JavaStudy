package com.tanhua.dubbo.service;

import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.dubbo.pojo.TimeLine;
import com.tanhua.dubbo.pojo.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * 异步写入到好友的时间线表中
 */
@Service
public class TimeLineService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Async //异步执行，原理：底层开一个线程去执行该方法
    public CompletableFuture<String> saveTimeLine(Publish publish) {
        //查询好友列表
        Query query = Query.query(Criteria.where("userId").is(publish.getUserId()));
        List<Users> usersList = this.mongoTemplate.find(query, Users.class);

        //将自己写入到集合中（自己作为自己的好友）
        Users my = new Users();
        my.setUserId(publish.getUserId());
        my.setFriendId(publish.getUserId());
        usersList.add(my);

        for (Users users : usersList) {
            TimeLine timeLine = new TimeLine();
            timeLine.setId(ObjectId.get());
            timeLine.setUserId(publish.getUserId());
            timeLine.setPublishId(publish.getId());
            timeLine.setDate(publish.getCreated());

            //写入到好友的时间线表
            this.mongoTemplate.save(timeLine, "quanzi_time_line_" + users.getFriendId());

            //TODO 事务问题
        }

        return CompletableFuture.completedFuture("ok");
    }
}
