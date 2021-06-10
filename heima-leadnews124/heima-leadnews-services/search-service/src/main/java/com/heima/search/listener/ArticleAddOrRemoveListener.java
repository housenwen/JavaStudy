package com.heima.search.listener;
import com.alibaba.fastjson.JSON;
import com.heima.common.constants.message.ArticleForEsConstants;
import com.heima.feigns.article.ArticleFeign;
import com.heima.model.search.vo.SearchArticleVo;
import com.heima.search.service.ArticleSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.Map;
@Component
@Slf4j
public class ArticleAddOrRemoveListener {
    @Autowired
    ArticleFeign articleFeign;
    @Autowired
    ArticleSearchService articleSearchService;
    @KafkaListener(topics = ArticleForEsConstants.ARTICLE_ELASTICSEARCH_ADD)
    public void addArticle(String message){
        Long articleId = (Long)JSON.parseObject(message, Map.class).get("articleId");
        log.info("搜索微服务 接收到添加文章到索引库消息==> {}",articleId);
        SearchArticleVo article = articleFeign.findArticle(articleId);
        if(article!=null){
            articleSearchService.saveArticle(article);
        }else {
            log.error("索引库要同步的文章信息 不存在==> {}",articleId);
        }

    }
    @KafkaListener(topics = ArticleForEsConstants.ARTICLE_ELASTICSEARCH_DELETE)
    public void removeArticle(String message){
        Long articleId = (Long)JSON.parseObject(message, Map.class).get("articleId");
        log.info("搜索微服务 接收到删除索引库文章消息==> {}",articleId);
        articleSearchService.delete(articleId);
    }
}