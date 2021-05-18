package com.tanhua.dubbo.api.impl;

import cn.hutool.core.util.ObjectUtil;
import com.mongodb.client.result.DeleteResult;
import com.tanhua.dubbo.api.UsersApi;
import com.tanhua.dubbo.pojo.Users;
import com.tanhua.dubbo.vo.PageInfo;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DubboService(version = "1.0.0")
public class UsersApiImpl implements UsersApi {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存好友关系
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @return
     */
    @Override
    public String saveUsers(Long userId, Long friendId) {
        //校验
        if (!ObjectUtil.isAllNotEmpty(userId, friendId)) {
            return null;
        }

        //校验他们是否已经是好友
        Query query = Query.query(Criteria.where("userId").is(userId)
                .and("friendId").is(friendId));
        long count = this.mongoTemplate.count(query, Users.class);
        if (count > 0) {
            return null;
        }

        //我与好友的关系
        Users users = new Users();
        ObjectId id1 = ObjectId.get();
        users.setId(id1);
        users.setUserId(userId);
        users.setFriendId(friendId);
        users.setDate(System.currentTimeMillis());
        Users save = this.mongoTemplate.save(users);

        //好友与我的关系
        users = new Users();
        ObjectId id = ObjectId.get();
        users.setId(id);
        users.setUserId(friendId);
        users.setFriendId(userId);
        users.setDate(System.currentTimeMillis());
        Users save1 = this.mongoTemplate.save(users);
        if(save==null||save1==null||UsersApiImpl.isHaveNull(save)||UsersApiImpl.isHaveNull(save1)){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("id1",userId);
            map.put("id2",friendId);
            this.rocketMQTemplate.convertAndSend("tanhua-server", map);
        }
        //TODO 事务问题
        return users.getId().toString();
    }
    public static boolean isHaveNull(Users users){
        return users.getFriendId()==null||users.getId()==null||users.getDate()==null||users.getUserId()==null;
    }

    @Override
    public Boolean removeUsers(Long userId, Long friendId) {
        //校验
        if (!ObjectUtil.isAllNotEmpty(userId, friendId)) {
            return false;
        }

        Query query1 = Query.query(Criteria.where("userId").is(userId)
                .and("friendId").is(friendId));

        Query query2 = Query.query(Criteria.where("userId").is(friendId)
                .and("friendId").is(userId));

        DeleteResult deleteResult1 = this.mongoTemplate.remove(query1, Users.class);
        DeleteResult deleteResult2 = this.mongoTemplate.remove(query2, Users.class);
        return deleteResult1.getDeletedCount() == 1 && deleteResult2.getDeletedCount() == 1;
    }

    @Override
    public List<Users> queryAllUsersList(Long userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return this.mongoTemplate.find(query, Users.class);
    }

    @Override
    public PageInfo<Users> queryUsersList(Long userId, Integer page, Integer pageSize) {
        PageInfo<Users> pageInfo = new PageInfo<>();
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageNum(page);

        //设置分页参数
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        Query query = Query.query(Criteria.where("userId").is(userId)).with(pageRequest);
        List<Users> usersList = this.mongoTemplate.find(query, Users.class);

        pageInfo.setRecords(usersList);
        return pageInfo;
    }
}
