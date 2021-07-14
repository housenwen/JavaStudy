package com.heima.article.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.mapper.AuthorMapper;
import com.heima.article.service.GeneratePageService;
import com.heima.common.exception.CustException;
import com.heima.file.service.FileStorageService;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleContent;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.enums.AppHttpCodeEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @作者 itcast
 * @创建日期 2021/7/12 9:23
 **/
@Service
@Slf4j
public class GeneratePageServiceImpl implements GeneratePageService {


    @Autowired
    ApArticleContentMapper apArticleContentMapper;
    @Autowired
    ApArticleMapper apArticleMapper;
    @Autowired
    AuthorMapper authorMapper;
    @Autowired
    Configuration configuration;
    @Resource(name = "minIOFileStorageService")
    FileStorageService fileStorageService;
    @Async
    @Override
    public void generateArticlePage(Long articleId) {
        // 1. 准备数据模型  authorApUserId  article  content
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId, articleId));
        if(apArticleContent == null || StringUtils.isBlank(apArticleContent.getContent())){
            log.error("文章内容为空，生成静态页失败  articleId:{}",articleId);
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"文章内容为空，生成静态页失败");
        }
        ApArticle apArticle = apArticleMapper.selectById(articleId);
        if(apArticle == null){
            log.error("文章为空，生成静态页失败  articleId:{}",articleId);
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"文章为空，生成静态页失败");
        }
        ApAuthor author = authorMapper.selectById(apArticle.getAuthorId());
        if(author == null){
            log.error("作者信息为空，生成静态页失败  articleId:{}",articleId);
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"作者信息为空，生成静态页失败");
        }
        Map params = new HashMap<>();
        params.put("authorApUserId",author.getUserId());
        params.put("article",apArticle);
        List<Map> list = JSONArray.parseArray(apArticleContent.getContent(), Map.class);
        params.put("content",list);
        // 2. 获取freemarker的模板
        try {
            Template template = configuration.getTemplate("article.ftl");
            // 3. 基于模板+数据模型 将替换后的内容 输出到一个输出流中
            StringWriter writer = new StringWriter();
            template.process(params,writer);
            // 4. 封装输入流，将静态页存入到minIo中
            InputStream in = new ByteArrayInputStream(writer.toString().getBytes());
            String path = fileStorageService.store("article", articleId + ".html", "text/html", in);
            log.info("页面静态完成， 页面路径: {}",path);
            // 5. 修改article的staticUrl字段
            apArticle.setStaticUrl(path);
            apArticleMapper.updateById(apArticle);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("服务器出现异常，生成静态页失败  articleId:{}",articleId);
            CustException.cust(AppHttpCodeEnum.SERVER_ERROR,"服务器出现异常，生成静态页失败");
        }
    }
}
