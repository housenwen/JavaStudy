package com.itcast.sw.pan;

import cn.hutool.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itcast.sw.pojo.ErrorInfo;
import com.itcast.sw.pojo.Errors;
import com.mongodb.client.result.DeleteResult;
import com.tanhua.dubbo.pojo.Users;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @ClassName LisetnForShiWu
 * @Description ToDO
 * @Author 执鉴
 * @Date 2021/5/8 21:20
 * Version 1.0
 **/

@Slf4j
@RocketMQMessageListener(topic = "tanhua-server",consumerGroup="shiwubuchang")
@Service
public class LisetnForShiWu implements RocketMQListener<String> {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    @Retryable(value = ErrorInfo.class, maxAttempts = 4, backoff = @Backoff(delay = 2000L, multiplier = 0))
    public void onMessage(String message) {
        try {
            HashMap<String, Object> hashMap = MAPPER.readValue(message, HashMap.class);
            Long id1 = (Long) hashMap.get("id1");
            Long id2 = (Long) hashMap.get("id2");
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
            if(save==null||save1==null||LisetnForShiWu.isHaveNull(save)||LisetnForShiWu.isHaveNull(save1)){
                List<Long> longList =new ArrayList<>();
                longList.add(id1);
                longList.add(id2);
                throw new ErrorInfo(longList);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Recover //全部重试失败后执行
    public void recover(ErrorInfo e) {
        mongoTemplate.save(new Errors(e));
    }
    public static boolean isHaveNull(Users users){
        return users.getFriendId()==null||users.getId()==null||users.getDate()==null||users.getUserId()==null;
    }
}
