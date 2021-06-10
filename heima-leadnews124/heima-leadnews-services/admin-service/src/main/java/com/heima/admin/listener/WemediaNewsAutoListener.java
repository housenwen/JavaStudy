package com.heima.admin.listener;

import com.heima.admin.service.WemediaNewsAutoScanService;
import com.heima.common.constants.message.NewsAutoScanConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2021/5/30 15:58
 **/
@Component
@Slf4j
public class WemediaNewsAutoListener {
    @Autowired
    WemediaNewsAutoScanService wemediaNewsAutoScanService;
    @KafkaListener(topics = NewsAutoScanConstants.WM_NEWS_AUTO_SCAN_TOPIC)
    public void newsAutoScanHandler(String newsId){
        log.info("admin微服务接收到kafka消息 ，待审核的文章id为==>{}",newsId);
        wemediaNewsAutoScanService.autoScanByMediaNewsId(Integer.valueOf(newsId));
    }
}
