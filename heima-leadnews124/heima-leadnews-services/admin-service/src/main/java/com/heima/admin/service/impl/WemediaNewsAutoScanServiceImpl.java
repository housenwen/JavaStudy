package com.heima.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.heima.admin.mapper.ChannelMapper;
import com.heima.admin.mapper.SensitiveMapper;
import com.heima.admin.service.WemediaNewsAutoScanService;
import com.heima.aliyun.GreenImageScan;
import com.heima.aliyun.GreenTextScan;
import com.heima.common.constants.message.ArticleForEsConstants;
import com.heima.common.exception.CustomException;
import com.heima.feigns.article.ArticleFeign;
import com.heima.feigns.wemedia.WemediaFeign;
import com.heima.model.admin.pojo.AdChannel;
import com.heima.model.admin.pojo.AdSensitive;
import com.heima.model.article.dto.ArticleDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.pojo.WmNews;
import com.heima.model.wemedia.pojo.WmUser;
import com.heima.utils.common.SensitiveWordUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @作者 itcast
 * @创建日期 2021/5/30 10:09
 **/
@Service
@Slf4j //  logback
public class WemediaNewsAutoScanServiceImpl implements WemediaNewsAutoScanService {
    @Value("${file.oss.web-site}")
    String webSite;
    @Autowired
    WemediaFeign wemediaFeign;
    @GlobalTransactional
    @Override
    public void autoScanByMediaNewsId(Integer id) {
        log.info("=====自动发布文章方法被触发 ======= 当前处理的文章id ==> {}",id);
        // 1. 检查文章id
        if(id == null){
            log.error("文章自动审核失败  ,  id为空");
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"文章id为空");
        }
        // 2. 根据文章id远程获取文章信息
        WmNews wmNews = wemediaFeign.findById(id);
        if(wmNews == null){
            log.error("文章自动审核失败  ,  远程查询自媒体文章失败");
            throw new CustomException(AppHttpCodeEnum.DATA_NOT_EXIST,"对应的自媒体文章不存在");
        }
        // 3. 检查文章状态
        // 4. 如果文章状态为 4 或 8 并且 发布时间小于等于当前时间   ==> 调用发布方法
        long now = System.currentTimeMillis();
        if((wmNews.getStatus().equals(WmNews.Status.ADMIN_SUCCESS.getCode())
                || wmNews.getStatus().equals(WmNews.Status.SUCCESS.getCode()))
                &&wmNews.getPublishTime().getTime()<=now){
            publishArticle(wmNews);
            log.info("自媒体文章被发布 {}",id);
            return;
        }
        // 5. 如果文章状态为 1   代表需要审核该文章
        if(wmNews.getStatus().equals(WmNews.Status.SUBMIT.getCode())){
            // 5.1 抽取文章内容中的文本和图片
            // key:content  value: 文本内容      key: images   value: List<String> url
            Map<String,Object> contentAndImages = handleTextAndImages(wmNews);
            // 5.2 DFA自管理敏感词审核
            boolean isSensitive = handleSensitive((String)contentAndImages.get("content"),wmNews);
            if(! isSensitive) return;
            log.info("=====当前处理的文章 通过敏感词DFA审核  id ==> {}",id);
            // 5.3 阿里云文本审核
            //     审核失败: 状态为 2   不确定: 状态改为 3
            boolean isTextScan = handleTextScan((String)contentAndImages.get("content"),wmNews);
            if(! isTextScan) return;
            log.info("=====当前处理的文章 通过阿里云文本审核  id ==> {}",id);
            // 5.4 阿里云图片审核
            //     审核失败: 状态为 2   不确定: 状态改为 3
            boolean isImageScan = handleImageScan((List)contentAndImages.get("images"),wmNews);
            if(! isImageScan) return;
            log.info("=====当前处理的文章 通过阿里云图片审核  id ==> {}",id);
            // 5.5 如果发布时间 大于 当前时间
            //     审核通过: 状态 8
            if(wmNews.getPublishTime().getTime() > now){
                updateWmNews(wmNews,(short)8,"审核通过");
                return;
            }
            // 5.6 调用对应方法
            publishArticle(wmNews);

        }
    }

    @Autowired
    GreenImageScan greenImageScan;
    /**
     * 阿里云图片审核
     * @param images
     * @param wmNews
     * @return
     */
    private boolean handleImageScan(List images, WmNews wmNews) {
        boolean flag = true;
        try {
            Map map = greenImageScan.imageUrlScan(images);
            String suggestion = (String)map.get("suggestion");
            if("review".equals(suggestion)){
                flag = false;
                updateWmNews(wmNews,(short)3,"图片中包含不确定因素，转为人工审核");
            }
            if("block".equals(suggestion)){
                flag = false;
                updateWmNews(wmNews,(short)2,"图片违规");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            updateWmNews(wmNews,(short)3,"阿里云审核失败，转为人工审核");
        }
        return flag;
    }

    @Autowired
    GreenTextScan greenTextScan;
    /**
     * 阿里云文本审核
     * @param content
     * @param wmNews
     * @return
     */
    private boolean handleTextScan(String content, WmNews wmNews) {
        boolean flag = true;
        try {
            Map map = greenTextScan.greenTextScan(content);
            String suggestion = (String)map.get("suggestion");
            if("review".equals(suggestion)){
                flag = false;
                updateWmNews(wmNews,(short)3,"内容中包含不确定因素，转为人工审核");
            }
            if("block".equals(suggestion)){
                flag = false;
                updateWmNews(wmNews,(short)2,"文本中包含敏感词汇");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
            updateWmNews(wmNews,(short)3,"阿里云审核失败，转为人工审核");
        }
        return flag;
    }

    @Autowired
    SensitiveMapper sensitiveMapper;

    /**
     * 自管理敏感词DFA审核
     * //      审核失败    :  修改文章状态为 2
     * @param content
     * @return
     */
    private boolean handleSensitive(String content,WmNews wmNews) {
        boolean flag = true;
        List<AdSensitive> adSensitives = sensitiveMapper.selectList(null);
        if(adSensitives == null && adSensitives.size()==0){
            return flag;
        }
        // 敏感词内容列表
        List<String> sensitiveList =
                adSensitives.stream().map(AdSensitive::getSensitives).collect(Collectors.toList());
        // 基于DFA算法生成DFA数据模型 (一个大map 套着一堆 小map)
        SensitiveWordUtil.initMap(sensitiveList);
        // 基于DFA算法进行敏感词检测
        Map<String, Integer> sensitiveMap = SensitiveWordUtil.matchWords(content);
        if(sensitiveMap!= null && sensitiveMap.size()>0){
            // 有敏感词
            flag = false;
            updateWmNews(wmNews,(short)2,"文章内容中包含敏感词: "+ sensitiveMap);
        }
        return flag;
    }
    /**
     * 修改自媒体文章状态
     * @param wmNews
     * @param status
     * @param reason
     */
    private void updateWmNews(WmNews wmNews, short status, String reason) {
        wmNews.setStatus(status);
        wmNews.setReason(reason);
        ResponseResult responseResult = wemediaFeign.updateWmNews(wmNews);
        if(!responseResult.getCode().equals(0)){
            log.error("远程修改 自媒体文章状态 失败   {}",responseResult.getErrorMessage());
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR,responseResult.getErrorMessage());
        }
    }

    /**
     * 抽取文章中 所有文本内容  和  所有图片内容
     * @param wmNews
     * @return map:         图片路径需要加上前缀
     */
    private Map<String, Object> handleTextAndImages(WmNews wmNews) {
        Map<String,Object> result = new HashMap<>();
        // "[ {type: image,  value: url},{type: text,  value: 内容1}{type: text,  value: 内容2}  ]"
        String content = wmNews.getContent();
        if(StringUtils.isBlank(content)){
            log.error("文章自动审核失败  ,  自媒体文章内容为空");
            throw new CustomException(AppHttpCodeEnum.PARAM_INVALID,"自媒体文章内容为空");
        }
        List<Map> contentMap = JSONArray.parseArray(content,Map.class);
        // 1. 抽取所有文本内容  美国       小明好美_hmtt_国家强大  拼接时加上混淆符号，防止因为拼接产生敏感词
        String contentStr = contentMap.stream()
                .filter(map -> "text".equals(map.get("type"))) // 过滤文本数据
                .map(map -> (String) map.get("value")) // 如果是文本，获取文本value
                .collect(Collectors.joining("_hmtt_"));// 将所有文本拼接成一个字符串
        contentStr = contentStr + "_hmtt_" + wmNews.getTitle();
        // 2. 抽取所有图片内容
        List<String> images = new ArrayList<>();
        contentMap.stream()
                .filter(map -> "image".equals(map.get("type")))
                .map(map -> (String) map.get("value"))
                .forEach(images::add);
        // 封面图有可能为空，所以做一下非空判断
        if(StringUtils.isNotBlank(wmNews.getImages())){  // xxxx.jpg,aaa.jpg
            Arrays.stream(wmNews.getImages().split(","))
                    .map(url -> webSite + url)
                    .forEach(images::add);
        }
        // 对集合内的数据进行去重
        images = images.stream().distinct().collect(Collectors.toList());
        result.put("content",contentStr);// 待审核的文本内容
        result.put("images",images);
        return result;
    }

    /**
     * 发布文章
     * @param wmNews
     */
    private void publishArticle(WmNews wmNews) {
        ResponseResult responseResult = saveApArticle(wmNews);
        if(!responseResult.getCode().equals(0)){
            log.error("文章自动审核失败  ,  远程调用发布文章接口失败");
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR,"远程保存文章失败");
        }
        wmNews.setArticleId((Long)responseResult.getData());// 用户关联保存成功的article文章
        updateWmNews(wmNews,(short)9,"发布成功");

        // ==============新加代码==============================================
        Map map = new HashMap();
        map.put("articleId",wmNews.getArticleId());
        kafkaTemplate.send(ArticleForEsConstants.ARTICLE_ELASTICSEARCH_ADD, JSON.toJSONString(map));
        log.info("文章已经自动发布，并发向搜索微服务发送 添加文章通知");
    }

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    ArticleFeign articleFeign;

    /**
     * 远程调用文章微服务
     * 保存文章三表信息  article   article_config  article_content
     * @param wmNews
     * @return
     */
    private ResponseResult saveApArticle(WmNews wmNews) {
        // 封装ArticleDto
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(wmNews,articleDto);
        // 拷贝时会将id拷贝到article的id中，手动制空
        articleDto.setId(null);
        // 有可能wmNews之前已经发布过，后来下架了
        Long articleId = wmNews.getArticleId();
        if(articleId!=null){
            // 设置到id字段中， 远程调用时，如果有这个ID 就修改  如果没有就添加
            articleDto.setId(articleId);
        }
        // 封面图片布局
        articleDto.setLayout(wmNews.getType());
        articleDto.setFlag((byte) 0);
        // 设置作者信息  根据wmNews关联wmUserId查询出 wmUser信息  (name  和 作者表的 name字段是一致)
        WmUser wmUser = wemediaFeign.findWmUserById(wmNews.getUserId());
        if(wmUser == null){
            log.error("文章自动审核失败  ,  获取关联自媒体用户失败");
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR,"获取关联自媒体用户失败");
        }
        articleDto.setAuthorName(wmUser.getName());
        // 设置频道信息
        AdChannel adChannel = channelMapper.selectById(wmNews.getChannelId());
        if(adChannel == null){
            log.error("文章自动审核失败  ,  获取关联频道信息失败");
            throw new CustomException(AppHttpCodeEnum.SERVER_ERROR,"获取关联频道信息失败");
        }
        articleDto.setChannelName(adChannel.getName());
        // 远程调用文章微服务 保存文章三表信息
        return articleFeign.saveArticle(articleDto);
    }
}
