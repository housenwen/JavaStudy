package com.heima.admin.listener;

import com.alibaba.fastjson.JSON;
import com.heima.admin.service.WemediaNewsAutoScanService;
import com.heima.common.constants.message.NewsAutoScanConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/11 15:15
 **/
@Component
@Slf4j
public class WemediaNewsAutoListener {
    @Autowired
    WemediaNewsAutoScanService wemediaNewsAutoScanService;
    @KafkaListener(topics = NewsAutoScanConstants.WM_NEWS_AUTO_SCAN_TOPIC
            ,errorHandler = "kafkaErrorHandler") // 指定异常处理器，不在进行重试
    public void wemediaNewsAutohandler(String msg){
        log.info("收到 主题:{} 的消息, 消息内容: {}",NewsAutoScanConstants.WM_NEWS_AUTO_SCAN_TOPIC,msg);
        if (StringUtils.isNotBlank(msg)) {
            Map map = JSON.parseObject(msg, Map.class);
            // 需要自动审核的文章id
            Object wmNewsId = map.get("wmNewsId");
            wemediaNewsAutoScanService.autoScanByMediaNewsId((Integer)wmNewsId);
        }
        log.info("完成 主题:{} 的消息 的处理 =================");
    }
}
