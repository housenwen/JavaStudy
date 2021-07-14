package com.heima.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.heima.admin.mapper.AdChannelMapper;
import com.heima.admin.mapper.AdSensitiveMapper;
import com.heima.admin.service.WemediaNewsAutoScanService;
import com.heima.aliyun.GreenImageScan;
import com.heima.aliyun.GreenTextScan;
import com.heima.common.exception.CustException;
import com.heima.feigns.ArticleFeign;
import com.heima.feigns.WemediaFeign;
import com.heima.model.admin.pojos.AdChannel;
import com.heima.model.admin.pojos.AdSensitive;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.wemedia.pojos.WmNews;
import com.heima.utils.common.SensitiveWordUtil;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @作者 itcast
 * @创建日期 2021/7/11 9:29
 **/
@Service
@Slf4j
public class WemediaNewsAutoScanServiceImpl implements WemediaNewsAutoScanService {
    @Autowired
    WemediaFeign wemediaFeign;
    @Value("${file.oss.web-site}")
    String webSite;
    /**
     * 自媒体文章审核
     * @param wmNewsId  自媒体文章id
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Override
    public void autoScanByMediaNewsId(Integer wmNewsId) {
        // 1.根据自媒体文章id 远程查询自媒体文章信息
        ResponseResult<WmNews> wmNewsRes = wemediaFeign.findById(wmNewsId);
        if(wmNewsRes.getCode().intValue()!=0){
            log.error("远程调用自媒体文章信息失败 文章id:{}   原因:{}",wmNewsId,wmNewsRes.getErrorMessage());
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"远程获取自媒体文章信息失败");
        }
        WmNews wmNews = wmNewsRes.getData();
        if(wmNews == null){
            log.error("未获取到自媒体文章信息 文章id:{}  ",wmNewsId);
            CustException.cust(AppHttpCodeEnum.DATA_NOT_EXIST,"未获取到自媒体文章信息");
        }
        short status = wmNews.getStatus();
        // 2.判断状态是否为 4或8 ，检查发布时间是否小于等于当前时间
        if((status==WmNews.Status.ADMIN_SUCCESS.getCode() || status==WmNews.Status.SUCCESS.getCode())
                && wmNews.getPublishTime().getTime() <= System.currentTimeMillis()){
            //    满足条件   发布文章
            publishArticle(wmNews);
            return;
        }
        // 3.判断状态是否为 1 (待审核) , 进行审核操作
        if(status == WmNews.Status.SUBMIT.getCode()){
            // 3.1 抽取文章中所有的文本内容 和 图片url路径
            // Map<String,Object >
            // key:  content 文本内容  value: 文本str
            // key:  images  图片内容  value: List<String>
            Map<String,Object> contentAndImages = handleTextAndImages(wmNews);
            // 3.2 使用DFA算法审核内容  (2 失败 )
            //  通过  true    不通过 false
            boolean sensitiveScan = handleSensitive((String)contentAndImages.get("content"),wmNews);
            if(!sensitiveScan) return;

            // 3.3 使用阿里云内容安全审核内容 (2 失败  3 人工审核 )
            boolean textScan = handleTextScan((String)contentAndImages.get("content"),wmNews);
            if(!textScan) return;

            // 3.4 使用阿里云内容安全审核图片 (2 失败  3 人工审核 )
            List<String> images = (List<String>)contentAndImages.get("images");
            if(images!=null && images.size()>0){
                boolean imageScan = handleImageScan(images,wmNews);
                if(!imageScan) return;
            }

            // 4. 判断当前时间是否小于发布时间
            if(new Date().before(wmNews.getPublishTime())){
                //      将文章状态修改为8 审核通过
                updateWmNews(wmNews,(short) 8,"审核通过");
                return;
            }
            // 5. 满足条件  发布文章
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
    private boolean handleImageScan(List<String> images, WmNews wmNews) {
        boolean flag = true;
        try {
            Map map = greenImageScan.imageUrlScan(images);
            String suggestion = (String)map.get("suggestion");
            if("review".equals(suggestion)){
                flag =false;
                updateWmNews(wmNews,(short) 3,"图片中有不确定的内容，需要转为人工审核");
            }
            if("block".equals(suggestion)){
                flag =false;
                updateWmNews(wmNews,(short) 2,"图片违规, 审核失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag =false;
            updateWmNews(wmNews,(short) 3,"阿里云图片审核调用失败，转为人工审核");
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
                flag =false;
                updateWmNews(wmNews,(short) 3,"文本中有不确定的内容，需要转为人工审核");
            }
            if("block".equals(suggestion)){
                flag =false;
                updateWmNews(wmNews,(short) 2,"文本中有违规的词汇, 审核失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag =false;
            updateWmNews(wmNews,(short) 3,"阿里云文本调用失败，转为人工审核");
        }
        return flag;
    }

    @Autowired
    AdSensitiveMapper adSensitiveMapper;

    private boolean handleSensitive(String content, WmNews wmNews) {
        boolean flag = true;
        // 1. 查询敏感词列表
        List<String> allSensitives = adSensitiveMapper.findAllSensitives();
        // 2. 封装为DFA数据模型
        SensitiveWordUtil.initMap(allSensitives);
        // 3. 基于DFA进行敏感词检测
        Map<String, Integer> resultMap = SensitiveWordUtil.matchWords(content);

        if (resultMap!=null && resultMap.size()>0) {
            // 包含敏感词
            flag = false;
            // 将文章状态修改为 2
            updateWmNews(wmNews,(short) 2,"内容中包含敏感词: "+resultMap);
        }
        return flag;
    }

    /**
     * 修改文章状态
     * @param wmNews 文章
     * @param status 状态
     * @param reason 原因
     */
    private void updateWmNews(WmNews wmNews, short status, String reason) {
        wmNews.setStatus(status);
        wmNews.setReason(reason);
        ResponseResult responseResult = wemediaFeign.updateWmNews(wmNews);
        if(responseResult.getCode().intValue()!=0){
            log.error("远程调用wemedia修改wmNews失败 :{}  ",responseResult.getErrorMessage());
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"远程调用wemedia修改wmNews失败");
        }
    }
    /**
     * 抽取文章中 所有文本内容 和 图片url
     * @param wmNews 文章
     * @return
     */
    private Map<String, Object> handleTextAndImages(WmNews wmNews) {
        if(StringUtils.isBlank(wmNews.getContent()) || wmNews.getContent().length()>10000){
            log.error("文章的内容参数错误 ==> {}" ,wmNews.getContent());
            CustException.cust(AppHttpCodeEnum.PARAM_INVALID);
        }
        // 0. 将文章内容 转化为  List<Map> contentMap
        List<Map> contentMap = JSONArray.parseArray(wmNews.getContent(), Map.class);
        // 1. 抽取 文本 ( 标题  _  内容)   国美    我爱我的祖国_hmtt_美丽的大草原
        StringBuilder stringBuilder = new StringBuilder(wmNews.getTitle() + "_hmtt_");
        //       1. filter 过滤 type=text数据    2. 获取对应value值
        String content = contentMap.stream()
                .filter(map -> "text".equals(map.get("type")))
                .map(map -> (String) map.get("value"))
                .collect(Collectors.joining("_hmtt_"));
        stringBuilder.append(content);
        // 2. 抽取 图片 (封面  _  内容)
        //      1. filter 过滤 type=image数据  2. 获取对应value值 3. 收集图片
        List<String> images = contentMap.stream()
                .filter(map -> "image".equals(map.get("type")))
                .map(map -> (String) map.get("value"))
                .collect(Collectors.toList());
        if (StringUtils.isNotBlank(wmNews.getImages())) {
            // 如果封面不为空 获取封面图片url    1.没有前缀    2.多个url 逗号
            List<String> coverUrls = Arrays.stream(wmNews.getImages().split(","))
                    .map(url -> webSite + url)
                    .collect(Collectors.toList());
            // 合并到上面的路径集合
            images.addAll(coverUrls);
        }
        // 去除封面和内容重复的图片
        images = images.stream().distinct().collect(Collectors.toList());
        Map result = new HashMap();
        result.put("content",stringBuilder.toString());
        result.put("images",images);
        return result;
    }
    @Autowired
    ArticleFeign articleFeign;
    /**
     * 发布文章方法
     * @param wmNews
     */
    private void publishArticle(WmNews wmNews) {
        //1. 远程调用saveArticle 保存3张表的信息
        ResponseResult responseResult = saveArticle(wmNews);
        if (responseResult.getCode().intValue()!=0) {
            log.error("远程保存apArticle信息失败： {}",responseResult.getErrorMessage());
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"远程保存apArticle信息失败");
        }
        //2. 修改自媒体文章状态  9
        wmNews.setArticleId((Long)responseResult.getData());
        updateWmNews(wmNews,(short) 9 ,"发布成功");
        //TODO 3. 通知ES索引库 添加对应文章
    }
    @Autowired
    AdChannelMapper adChannelMapper;
    /**
     * 远程调用feign保存三张表信息
     * @param wmNews
     * @return
     */
    private ResponseResult saveArticle(WmNews wmNews) {
        //1. 封装dto参数
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(wmNews,articleDto);
        // 如果之前没发布过  id为null 发布过为之前关联的articleId
        articleDto.setId(wmNews.getArticleId());
        articleDto.setLayout(wmNews.getType()); // 布局
        if(wmNews.getChannelId()!=null){
            // 设置频道信息
            AdChannel channel = adChannelMapper.selectById(wmNews.getChannelId());
            articleDto.setChannelName(channel.getName());
        }
        // 0 普通文章
        articleDto.setFlag((byte) 0);
        // 自媒体用户id 用于在article服务中查询相关作者信息
        articleDto.setWmUserId(wmNews.getUserId());
        // 远程调用 article微服务保存3张表方法
        return articleFeign.saveArticle(articleDto);
    }
}
