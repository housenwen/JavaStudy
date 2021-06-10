package com.heima.article.listener;

import com.alibaba.fastjson.JSON;
import com.heima.article.service.ApArticleService;
import com.heima.common.constants.message.HotArticleConstants;
import com.heima.model.article.mess.ArticleVisitStreamMess;
import com.heima.model.article.pojo.ApArticleContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 监听 流处理 处理后的消息内容
 * @作者 itcast
 * @创建日期 2021/6/9 15:22
 **/
@Component
@Slf4j
public class ArticleIncrHandleListener {
    @Autowired
    ApArticleService apArticleService;

    @KafkaListener(topics = HotArticleConstants.HOTARTICLE_INCR_HANDLE_OUPUT_TOPIC)
    public void receiveMessage(String message){
        log.info("接收到 流式处理后的消息 消息内容====> {}",message);
        apArticleService.updateApArticle(JSON.parseObject(message, ArticleVisitStreamMess.class));
        log.info("更新文章热度完毕  ====> ");
    }
}
