package com.heima.article.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.service.ApArticleConfigService;
import com.heima.common.constants.message.WmNewsMessageConstants;
import com.heima.model.article.pojo.ApArticleConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/6/2 11:08
 **/
@Component
@Slf4j
public class ArticleIsDownListener {
    @Autowired
    ApArticleConfigService apArticleConfigService;
    @KafkaListener(topics = WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC)
    public void receiveMessage(String mapJson){
        log.info(" 接收到 文章上下架的通知消息, 消息的内容为==>{}",mapJson);
        Map map = JSON.parseObject(mapJson, Map.class);
        Long articleId = (Long)map.get("articleId");
        Object enable = map.get("enable");
        boolean isDown =  enable.equals(1)? false:true; //  true  0 下架   false 上架 1
        // 根据接收到的消息  修改ap_article_config配置信息
        apArticleConfigService.update(
                Wrappers.<ApArticleConfig>lambdaUpdate()
                        .set(ApArticleConfig::getIsDown,isDown)
                        .eq(ApArticleConfig::getArticleId,articleId)
        );
    }
}
