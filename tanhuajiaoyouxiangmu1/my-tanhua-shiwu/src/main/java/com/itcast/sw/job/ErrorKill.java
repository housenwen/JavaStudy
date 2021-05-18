package com.itcast.sw.job;

import com.itcast.sw.pojo.ErrorInfo;
import com.itcast.sw.pojo.Errors;
import com.tanhua.dubbo.pojo.Users;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * @ClassName ErrorKill
 * @Description ToDO
 * @Author 执鉴
 * @Date 2021/5/8 23:51
 * Version 1.0
 **/
@Service
public class ErrorKill {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Scheduled(cron = "0 0/5 * * * *") //每30分钟执行
    public void run() {
        List<Errors> all = mongoTemplate.findAll(Errors.class);
        for (Errors errors : all) {
            ErrorInfo info = errors.getInfo();
            List<Long> ids = info.getId();
                Long id1 =ids.get(0);
                Long id2 = ids.get(1);
                Query query = new Query(new Criteria().where("userId").is(id1).and("friendId").is(id2));
                Query query1 = new Query(new Criteria().where("userId").is(id2).and("friendId").is(id1));
                mongoTemplate.remove(query, Users.class);
                mongoTemplate.remove(query1, Users.class);
                Users users = new Users();
                users.setId(ObjectId.get());
                users.setUserId(id1);
                users.setFriendId(id2);
                users.setDate(System.currentTimeMillis());
                Users save = this.mongoTemplate.save(users);

                //好友与我的关系
                users = new Users();
                users.setId(ObjectId.get());
                users.setUserId(id2);
                users.setFriendId(id1);
                users.setDate(System.currentTimeMillis());
                Users save1 = this.mongoTemplate.save(users);
        }
    }
}
