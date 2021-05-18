package com.tanhua.manage.msg;

import cn.hutool.core.convert.Convert;
import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tanhua.dubbo.api.QuanZiApi;
import com.tanhua.dubbo.pojo.Publish;
import com.tanhua.manage.enums.AutoAuditStateEnum;
import com.tanhua.manage.enums.LogTypeEnum;
import com.tanhua.manage.enums.PublishAuditStateEnum;
import com.tanhua.manage.pojo.PublishAuditLog;
import com.tanhua.manage.pojo.PublishInfo;
import com.tanhua.manage.service.HuaWeiUGCService;
import com.tanhua.manage.service.PublishAuditLogService;
import com.tanhua.manage.service.PublishInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RocketMQMessageListener(topic = "tanhua-quanzi",
        consumerGroup = "tanhua-quanzi-audit-consumer")
@Slf4j
public class PublishMsgConsumer implements RocketMQListener<String> {

    @DubboReference(version = "1.0.0")
    private QuanZiApi quanZiApi;
    @Autowired
    private HuaWeiUGCService huaWeiUGCService;
    @Autowired
    private PublishInfoService publishInfoService;
    @Autowired
    private PublishAuditLogService publishAuditLogService;
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    @Value("${tanhua.auto-audit}")
    private Boolean autoAudit;


    @Override
    public void onMessage(String msg) {
        try {
            JsonNode jsonNode = MAPPER.readTree(msg);
            String publishId = jsonNode.get("publishId").asText();
            //消息类型 1-发动态，2-浏览动态， 3-点赞， 4-喜欢， 5-评论，6-取消点赞，7-取消喜欢
            Integer type = jsonNode.get("type").asInt();
            Long userId = jsonNode.get("userId").asLong();
            Long date = jsonNode.get("date").asLong();
            Publish publish = quanZiApi.queryPublishById(publishId);
            if (publish != null) {
                log.info("收到动态消息，将其转向 tanhua-log topic:" + msg);
                Map<String, Object> msgMap = new HashMap<>();
                msgMap.put("userId", userId);
                msgMap.put("date", date);
                switch (type) {
                    case 1:
                        // 处理发动态
                        processSaveMovements(publish);
                        msgMap.put("type", LogTypeEnum.MOVEMENTS_ADD.getValue());
                        sendLogMessage(msgMap);
                        break;
                    case 3:
                        // 处理点赞
                        processLikeMovements(publish);
                        msgMap.put("type", LogTypeEnum.MOVEMENTS_LIKE.getValue());
                        sendLogMessage(msgMap);
                        break;
                    case 4:
                        // 处理喜欢
                        processLoveMovements(publish);
                        msgMap.put("type", LogTypeEnum.MOVEMENTS_LOVE.getValue());
                        sendLogMessage(msgMap);
                        break;
                    case 5:
                        // 处理评论
                        processCommentMovements(publish);
                        msgMap.put("type", LogTypeEnum.MOVEMENTS_COMMENT.getValue());
                        sendLogMessage(msgMap);
                        break;
                    case 6:
                        // 处理取消点赞
                        processUnlikeMovements(publish);
                        msgMap.put("type", LogTypeEnum.MOVEMENTS_UNLIKE.getValue());
                        sendLogMessage(msgMap);
                        break;
                    case 7:
                        // 处理取消喜欢
                        processUnLoveMovements(publish);
                        msgMap.put("type", LogTypeEnum.MOVEMENTS_UNLOVE.getValue());
                        sendLogMessage(msgMap);
                        break;
                }
            }
        } catch (Exception e) {
            log.error("消息处理失败!" + e.getMessage());
        }
    }

/**
     * 处理取消喜欢消息
     *
     * @param publish 动态信息*/


    private void processUnLoveMovements(Publish publish) {
        PublishInfo info = publishInfoService.findInfoByPublishId(publish.getId().toString());
        if (info != null) {
            info.setLoveCount(info.getLoveCount() == 0 ? 0 : info.getLoveCount() - 1);
            publishInfoService.saveOrUpdate(info);
        }
    }

/**
     * 处理取消点赞消息
     *
     * @param publish 动态信息*/


    private void processUnlikeMovements(Publish publish) {
        PublishInfo info = publishInfoService.findInfoByPublishId(publish.getId().toString());
        if (info != null) {
            info.setLikeCount(info.getLikeCount() == 0 ? 0 : info.getLikeCount() - 1);
            publishInfoService.saveOrUpdate(info);
        }
    }

/**
     * 处理评论消息
     *
     * @param publish 动态信息*/


    private void processCommentMovements(Publish publish) {
        PublishInfo info = publishInfoService.findInfoByPublishId(publish.getId().toString());
        if (info != null) {
            info.setCommentCount(info.getCommentCount() + 1);
            publishInfoService.saveOrUpdate(info);
        }
    }
/*
*
     * 处理喜欢消息
     *
     * @param publish 动态信息
     */

    private void processLoveMovements(Publish publish) {
        PublishInfo info = publishInfoService.findInfoByPublishId(publish.getId().toString());
        if (info != null) {
            info.setLoveCount(info.getLoveCount() + 1);
            publishInfoService.saveOrUpdate(info);
        }
    }

/**
     * 处理点赞消息
     *
     * @param publish 动态信息*/


    private void processLikeMovements(Publish publish) {
        PublishInfo info = publishInfoService.findInfoByPublishId(publish.getId().toString());
        if (info != null) {
            info.setLikeCount(info.getLikeCount() + 1);
            publishInfoService.saveOrUpdate(info);
        }
    }

/**
     * 处理发动态消息
     *
     * @param publish 动态信息
     * @throws Exception*/


    private void processSaveMovements(Publish publish) {
        log.info("处理发动态消息:" + publish);
        //幂等校验，防止重复消费
        PublishInfo source = publishInfoService.findInfoByPublishId(publish.getId().toString());
        log.info("处理发动态消息source:" + source);
        if (source != null) {
            return;
        }

        // 写入初始化信息
        PublishInfo info = new PublishInfo();
        info.setUserId(publish.getUserId());
        info.setPublishId(publish.getId().toString());
        info.setCreateDate(publish.getCreated());

        if (autoAudit) {  // 机器审核
            AutoAuditStateEnum textAutoAuditStateEnum = this.huaWeiUGCService.textContentCheck(publish.getText());
            AutoAuditStateEnum imageAutoAuditStateEnum = this.huaWeiUGCService.imageContentCheck(Convert.toStrArray(publish.getMedias()));
            if (textAutoAuditStateEnum == AutoAuditStateEnum.REVIEW || imageAutoAuditStateEnum == AutoAuditStateEnum.REVIEW) {
                info.setState(PublishAuditStateEnum.WAIT_MAUL.getValue());
            }else if(textAutoAuditStateEnum == AutoAuditStateEnum.PASS && imageAutoAuditStateEnum == AutoAuditStateEnum.PASS){
                info.setState(PublishAuditStateEnum.AUTO_PASS.getValue());
            }else{
                info.setState(PublishAuditStateEnum.AUTO_BLOCK.getValue());
            }
        } else {
            //不进行机器审核
            info.setState(PublishAuditStateEnum.WAIT_MAUL.getValue());
        }

        publishInfoService.saveOrUpdate(info);

        if(autoAudit){
            //保存自动审核的日志
            PublishAuditLog log = new PublishAuditLog(null, info.getPublishId(), PublishAuditStateEnum.WAIT.getValue(), info.getState());
            publishAuditLogService.save(log);
        }
    }

    private void sendLogMessage(Map<String, Object> msg) {
        rocketMQTemplate.convertAndSend("tanhua-log", msg);
    }

}
