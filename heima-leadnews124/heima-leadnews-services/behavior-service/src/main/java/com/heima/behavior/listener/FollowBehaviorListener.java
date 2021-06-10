package com.heima.behavior.listener;

import com.alibaba.fastjson.JSON;
import com.heima.behavior.service.ApFollowBehaviorService;
import com.heima.common.constants.message.FollowBehaviorConstants;
import com.heima.model.behavior.dto.FollowBehaviorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2021/6/4 10:46
 **/
@Component
@Slf4j
public class FollowBehaviorListener {
    @Autowired
    ApFollowBehaviorService apFollowBehaviorService;
    @KafkaListener(topics = FollowBehaviorConstants.FOLLOW_BEHAVIOR_TOPIC)
    public void followBehaviorHandler(String msg){
        log.info("接收到关注行为消息 , 消息内容:  {}",msg);
        FollowBehaviorDto dto = JSON.parseObject(msg, FollowBehaviorDto.class);
        apFollowBehaviorService.saveFollowBehavior(dto);
    }
}
