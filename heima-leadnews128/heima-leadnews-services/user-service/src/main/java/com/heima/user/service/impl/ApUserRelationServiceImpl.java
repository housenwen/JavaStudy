package com.heima.user.service.impl;

import com.heima.common.constants.user.UserRelationConstants;
import com.heima.common.exception.CustException;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.dtos.UserRelationDto;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.service.ApUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @作者 itcast
 * @创建日期 2021/7/14 14:46
 **/
@Service
public class ApUserRelationServiceImpl implements ApUserRelationService {
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    @Override
    public ResponseResult follow(UserRelationDto dto) {
        // 1. 校验参数    authorApUserId   必须登录   operation 0  1
        if(dto.getAuthorApUserId() == null){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"作者对应的userId不存在");
        }
        Short operation = dto.getOperation();
        if(operation == null || (operation.intValue()!=0 && operation.intValue()!=1)){
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID,"关注类型错误");
        }
        ApUser user = AppThreadLocalUtils.getUser();
        if(user == null){
            CustException.cust(AppHttpCodeEnum.NEED_LOGIN);
        }
        Integer loginId = user.getId();
        Integer followId = dto.getAuthorApUserId();
        try {
            //  开启  redis  的事务
            redisTemplate.multi();
            // 2. 判断operation 是0  是1
            if(operation.intValue() == 0){
                // 2.1  是0  关注
                //    校验之前有没有关注过   zscore
                //                         参数1： key   参数2: 要查询集合元素
                Double score = redisTemplate.opsForZSet()
                        .score(UserRelationConstants.FOLLOW_LIST + loginId, String.valueOf(followId));
                if(score!=null){
                    CustException.cust(AppHttpCodeEnum.DATA_EXIST,"您已关注，请勿重复关注");
                }
                //    没有关注过    zadd  follow:我的id   作者id
                //                          参数1: key  参数2 集合元素  参数3: score
                redisTemplate.opsForZSet().add(UserRelationConstants.FOLLOW_LIST + loginId,String.valueOf(followId),System.currentTimeMillis());
                //                zadd  fans:作者id    我的id
                redisTemplate.opsForZSet().add(UserRelationConstants.FANS_LIST + followId,String.valueOf(loginId),System.currentTimeMillis());
            }else {
                // 2.2  是1  取关
                //     zrem  follow:我的id   作者id
                redisTemplate.opsForZSet().remove(UserRelationConstants.FOLLOW_LIST + loginId,String.valueOf(followId));
                //     zrem  fans:作者id    我的id
                redisTemplate.opsForZSet().remove(UserRelationConstants.FANS_LIST + followId,String.valueOf(loginId));
            }
            //  提交事务
            redisTemplate.exec();
        }catch (Exception e){
            e.printStackTrace();
            // 如果有异常   取消事务
            redisTemplate.discard(); // 取消事务
            throw e;
        }
        return ResponseResult.okResult();
    }
}
