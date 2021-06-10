package com.heima.user.service.impl;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.common.constants.message.FollowBehaviorConstants;
import com.heima.common.exception.CustomException;
import com.heima.feigns.article.ArticleFeign;
import com.heima.model.article.pojo.ApAuthor;
import com.heima.model.behavior.dto.FollowBehaviorDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.threadlocal.AppThreadLocalUtils;
import com.heima.model.user.dto.UserRelationDto;
import com.heima.model.user.pojo.ApUser;
import com.heima.model.user.pojo.ApUserFan;
import com.heima.model.user.pojo.ApUserFollow;
import com.heima.user.mapper.ApUserFanMapper;
import com.heima.user.mapper.ApUserFollowMapper;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.service.ApUserRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @作者 itcast
 * @创建日期 2021/6/4 8:57
 **/
@Service
public class ApUserRelationServiceImpl implements ApUserRelationService {

    @Autowired
    ArticleFeign articleFeign;

    @Autowired
    ApUserMapper apUserMapper;

    @Autowired
    ApUserFollowMapper apUserFollowMapper;

    @Autowired
    ApUserFanMapper apUserFanMapper;

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult follow(UserRelationDto dto) {
        // 1. 检查参数  (作者id   操作行为 0    1)
        if(dto.getAuthorId() == null){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"作者ID不能为空");
        }
        if(dto.getOperation() == null || (dto.getOperation()!=0 && dto.getOperation() !=1)){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"操作状态错误");
        }
        // 2. 远程调用作者(author)对应的user信息
        ApAuthor author = articleFeign.selectById(dto.getAuthorId());
        if(author == null){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"对应的作者信息不存在");
        }
        ApUser followUser = apUserMapper.selectById(author.getUserId());
        if(followUser == null){
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"作者用户信息不存在");
        }
        // 3. 获取当前登录用户
        ApUser loginUser = AppThreadLocalUtils.getUser();
        if(loginUser == null){
            throw new CustomException(AppHttpCodeEnum.NEED_LOGIN);
        }
        if(followUser.getId().equals(loginUser.getId())){
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"不可以关注自己");
        }
        // 4. 如果操作行为是 0  关注该作者
        if(dto.getOperation() == 0){
            return followByUserId(loginUser,followUser,dto.getArticleId());
        }else {
            // 5. 如果操作行为是 1  取关该作者
            return followCancelByUserId(loginUser,followUser);
        }
    }

    /**
     * 取消关注
     * @param loginUser
     * @param followUser
     * @return
     */
    private ResponseResult followCancelByUserId(ApUser loginUser, ApUser followUser) {
        //1. 删除关注表相关信息
        apUserFollowMapper.delete(Wrappers.<ApUserFollow>lambdaQuery().eq(ApUserFollow::getUserId,loginUser.getId())
                .eq(ApUserFollow::getFollowId,followUser.getId()));
        //2. 删除粉丝表相关信息
        apUserFanMapper.delete(Wrappers.<ApUserFan>lambdaQuery().eq(ApUserFan::getUserId,followUser.getId())
                .eq(ApUserFan::getFansId,loginUser.getId()));
        return ResponseResult.okResult();
    }
    /**
     * 关注作者
     * @param loginUser
     * @param followUser
     * @return
     */
    private ResponseResult followByUserId(ApUser loginUser, ApUser followUser,Long articleId) {
        // 1. 先查询  是否关注过
        ApUserFollow apUserFollow = apUserFollowMapper.selectOne(Wrappers.<ApUserFollow>lambdaQuery()
                .eq(ApUserFollow::getUserId, loginUser.getId())
                .eq(ApUserFollow::getFollowId, followUser.getId()));
        if(apUserFollow != null){
            throw new CustomException(AppHttpCodeEnum.DATA_EXIST,"你已经关注过该作者 ，请勿重复关注");
        }
        // 2. 创建关注信息  保存
        apUserFollow = new ApUserFollow();
        apUserFollow.setUserId(loginUser.getId());
        apUserFollow.setFollowId(followUser.getId());
        apUserFollow.setFollowName(followUser.getName());
        apUserFollow.setLevel((short)0);
        apUserFollow.setIsNotice(false);
        apUserFollow.setCreatedTime(new Date());
        apUserFollowMapper.insert(apUserFollow);
        // 3. 创建粉丝信息  保存
        ApUserFan apUserFan = apUserFanMapper.selectOne(Wrappers.<ApUserFan>lambdaQuery()
                .eq(ApUserFan::getUserId, followUser.getId())
                .eq(ApUserFan::getFansId, loginUser.getId()));
        if(apUserFan == null){
            apUserFan = new ApUserFan();
            apUserFan.setUserId(followUser.getId());
            apUserFan.setFansId(Long.valueOf(loginUser.getId()));
            ApUser apUser = apUserMapper.selectById(loginUser.getId());
            apUserFan.setFansName(apUser.getName());
            apUserFan.setLevel((short)0);
            apUserFan.setCreatedTime(new Date());
            apUserFan.setIsDisplay(false);
            apUserFan.setIsShieldLetter(false);
            apUserFan.setIsShieldComment(false);
            apUserFanMapper.insert(apUserFan);
        }
        // 关注后，要保存关注行为
        // 使用mq将关注行为发送到行为微服务
        FollowBehaviorDto dto = new FollowBehaviorDto();
        dto.setArticleId(articleId);
        dto.setFollowId(followUser.getId());
        dto.setUserId(loginUser.getId());
        kafkaTemplate.send(FollowBehaviorConstants.FOLLOW_BEHAVIOR_TOPIC, JSON.toJSONString(dto));
        return ResponseResult.okResult();
    }
}
