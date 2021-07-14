package com.heima.article.listener;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.mapper.ApArticleConfigMapper;
import com.heima.article.service.ApArticleConfigService;
import com.heima.common.constants.message.WmNewsMessageConstants;
import com.heima.model.article.pojos.ApArticleConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/12 16:02
 **/
@Component
@Slf4j
public class ArticleIsDownListener {

    @Autowired
    ApArticleConfigService apArticleConfigService;

    @KafkaListener(topics = WmNewsMessageConstants.WM_NEWS_UP_OR_DOWN_TOPIC)
    public void receiveMessage(String msg){
        log.info(" 收到上下架消息  消息内容: {}",msg);
        if(StringUtils.isNotBlank(msg)){
            Map map = JSON.parseObject(msg, Map.class);
            Object articleId = map.get("articleId");
            Object enable = map.get("enable");
            // enable  1 上架   0 下架
            // isDown  true 下架   false 上架
            boolean isDown = (Integer)enable == 0 ? true:false;
            // 修改配置中 isDown上下架字段
            apArticleConfigService.update(Wrappers.<ApArticleConfig>lambdaUpdate()
                                                    .set(ApArticleConfig::getIsDown,isDown)
                                                    .eq(ApArticleConfig::getArticleId,articleId)
            );
        }
    }
}
