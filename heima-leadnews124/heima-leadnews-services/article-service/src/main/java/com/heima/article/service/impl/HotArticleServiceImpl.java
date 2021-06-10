package com.heima.article.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.service.HotArticleService;
import com.heima.common.constants.article.ArticleConstants;
import com.heima.feigns.admin.AdminFeign;
import com.heima.model.admin.pojo.AdChannel;
import com.heima.model.article.pojo.ApArticle;
import com.heima.model.article.vo.HotArticleVo;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @作者 itcast
 * @创建日期 2021/6/9 11:00
 **/
@Service
@Slf4j
public class HotArticleServiceImpl implements HotArticleService {
    @Autowired
    ApArticleMapper apArticleMapper;

    @Override
    public void computeHotArticle() {
        // 1. 获取近5天的文章数据
        // 1.1 计算5天前当前的时间  yyyy-MM-dd HH:mm:ss
        String dateStr = LocalDateTime.now().minusDays(5)
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toString();
        // 1.2 查询近5天的文章 publishTime > 5天前的时间
        List<ApArticle> apArticles = apArticleMapper.loadArticles(dateStr);
        // 2. 计算文章的分值 (封装vo集合)
        List<HotArticleVo> hotArticleVoList = getHotArticleVoList(apArticles);
        // 3. 按照频道 缓存文章
        cacheToRedisByTag(hotArticleVoList);
    }

    @Autowired
    AdminFeign adminFeign;

    /**
     * 按照频道
     * 缓存文章到redis
     * @param hotArticleVoList
     */
    private void cacheToRedisByTag(List<HotArticleVo> hotArticleVoList) {
        // 1. 远程查询频道数据
        ResponseResult responseResult = adminFeign.selectAllChannel();
        // 解析全部频道列表
        List<AdChannel> channelList = JSONArray.parseArray(JSON.toJSONString(responseResult.getData()), AdChannel.class);
        // 2. 为每个频道 缓存对应频道热点排行前30的文章
        channelList.forEach(channel -> {
            List<HotArticleVo> hotArticleByChannel = hotArticleVoList.stream()
                    .filter(articleVo -> articleVo.getChannelId().equals(channel.getId()))
                    .sorted(Comparator.comparing(HotArticleVo::getScore).reversed())
                    .limit(30)
                    .collect(Collectors.toList());
            // 缓存当前频道文章
            cacheToRedis(hotArticleByChannel,ArticleConstants.HOT_ARTICLE_FIRST_PAGE+channel.getId());
        });
        // 3. 为推荐频道 缓存所有数据中热度排行前30的文章
        List<HotArticleVo> hotArticleByAll = hotArticleVoList.stream()
                .sorted(Comparator.comparing(HotArticleVo::getScore).reversed())
                .limit(30)
                .collect(Collectors.toList());
        cacheToRedis(hotArticleByAll,ArticleConstants.HOT_ARTICLE_FIRST_PAGE+ArticleConstants.DEFAULT_TAG);
    }
    @Autowired
    RedisTemplate<String,String> redisTemplate;
    /**
     * 缓存文章集合 到 redis
     * @param hotArticleByChannel
     * @param cacheKey
     */
    private void cacheToRedis(List<HotArticleVo> hotArticleByChannel, String cacheKey) {
        redisTemplate.opsForValue().set(cacheKey,JSON.toJSONString(hotArticleByChannel));
    }

    /**
     * 计算每一篇文章的分值
     * 封装vo
     * @param apArticles
     * @return
     */
    private List<HotArticleVo> getHotArticleVoList(List<ApArticle> apArticles) {
        List<HotArticleVo> hotArticleVoList = apArticles.stream()
                .map(apArticle -> {
                    HotArticleVo hotArticleVo = new HotArticleVo();
                    BeanUtils.copyProperties(apArticle, hotArticleVo);
                    // 计算文章得分
                    Integer score = computeScore(apArticle);
                    hotArticleVo.setScore(score);
                    return hotArticleVo;
                }).collect(Collectors.toList());
        return hotArticleVoList;
    }

    /**
     * 计算得分
     * @param apArticle
     * @return
     */
    private Integer computeScore(ApArticle apArticle) {
        Integer score = 0;
        // 阅读数量
        Integer views = apArticle.getViews();
        if(views!=null){
            score += views * ArticleConstants.HOT_ARTICLE_VIEW_WEIGHT;
        }
            // 点赞数量
        Integer likes = apArticle.getLikes();
        if(likes!=null){
            score += likes * ArticleConstants.HOT_ARTICLE_LIKE_WEIGHT;
        }
            // 评论数量
        Integer comment = apArticle.getComment();
        if(comment!=null){
            score += comment * ArticleConstants.HOT_ARTICLE_COMMENT_WEIGHT;
        }
            // 收藏数量
        Integer collection = apArticle.getCollection();
        if(collection!=null){
            score += collection * ArticleConstants.HOT_ARTICLE_COLLECTION_WEIGHT;
        }
        return score;
    }
}
