package com.heima.article.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.article.mapper.ApArticleConfigMapper;
import com.heima.article.mapper.ApArticleContentMapper;
import com.heima.article.mapper.ApArticleMapper;
import com.heima.article.mapper.AuthorMapper;
import com.heima.article.service.ApArticleService;
import com.heima.article.service.GeneratePageService;
import com.heima.common.constants.article.ArticleConstants;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.article.dtos.ArticleHomeDto;
import com.heima.model.article.pojos.ApArticle;
import com.heima.model.article.pojos.ApArticleConfig;
import com.heima.model.article.pojos.ApArticleContent;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {

    @Autowired
    AuthorMapper authorMapper;

    @Autowired
    ApArticleContentMapper apArticleContentMapper;

    @Autowired
    ApArticleConfigMapper apArticleConfigMapper;

    @Autowired
    GeneratePageService generatePageService;

    @Autowired
    ApArticleMapper apArticleMapper;

    @Value("${file.oss.web-site}")
    String webSite;

    @Value("${file.minio.readPath}")
    String readPath;

    @Override
    public ResponseResult saveArticle(ArticleDto articleDto) {
        log.info(" saveArticle 保存文章三表信息  参数: {}",articleDto);
        // 1. 解析dto 封装ap_article
        ApArticle apArticle = new ApArticle();
        BeanUtils.copyProperties(articleDto,apArticle);
        // 2. 补全article
        ApAuthor author = authorMapper.selectOne(Wrappers.<ApAuthor>lambdaQuery()
                .eq(ApAuthor::getWmUserId, articleDto.getWmUserId()));
        apArticle.setAuthorId(Long.valueOf(author.getId()));
        apArticle.setAuthorName(author.getName());
        // 3. 根据是否包含articleID判断是修改还是新增
        if(articleDto.getId() == null){
            // 新增
            apArticle.setCollection(0); // 收藏
            apArticle.setLikes(0); // 点赞
            apArticle.setComment(0); // 评论
            apArticle.setViews(0); // 阅读
            save(apArticle);

        }else {
            // 修改
            updateById(apArticle);
            // 如果之前包含config 和 content信息  删除掉
            apArticleContentMapper.delete(Wrappers.<ApArticleContent>lambdaQuery().eq(ApArticleContent::getArticleId,apArticle.getId()));
            apArticleConfigMapper.delete(Wrappers.<ApArticleConfig>lambdaQuery().eq(ApArticleConfig::getArticleId,apArticle.getId()));
        }
        // 4. 添加config信息
        ApArticleConfig config = new ApArticleConfig();
        config.setArticleId(apArticle.getId());
        config.setIsComment(true);
        config.setIsForward(true);
        config.setIsDown(false); // 上架
        config.setIsDelete(false); // 正常
        apArticleConfigMapper.insert(config);
        // 5. 添加content信息
        ApArticleContent content = new ApArticleContent();
        content.setArticleId(apArticle.getId());
        content.setContent(articleDto.getContent());
        apArticleContentMapper.insert(content);
        // 6. TODO 异步生成文章静态页面
        log.info(" saveArticle 保存文章三表信息成功");
        // 返回新增后的apArticleId

        generatePageService.generateArticlePage(apArticle.getId());
        System.out.println("=======静态页面调用后的操作");
        return ResponseResult.okResult(apArticle.getId());
    }

    @Override
    public ResponseResult load(Short type, ArticleHomeDto dto) {
        //1. 检查参数 size == 10   min max == new Date()  tag == __all__
        Integer size = dto.getSize();
        if(size == null || size < 1){
            size = 10; // 设置一个size的默认值
            dto.setSize(size);
        }
        Date minBehotTime = dto.getMinBehotTime();
        if(minBehotTime == null){
            dto.setMinBehotTime(new Date());
        }
        Date maxBehotTime = dto.getMaxBehotTime();
        if(maxBehotTime == null){
            dto.setMaxBehotTime(new Date());
        }
        String tag = dto.getTag();
        if(StringUtils.isBlank(tag)){
            dto.setTag(ArticleConstants.DEFAULT_TAG);
        }
        // 2. 调用mapper查询结果
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, type);

        if(apArticles!= null && apArticles.size() > 0){
            for (ApArticle apArticle : apArticles) {
                apArticle.setStaticUrl(readPath + apArticle.getStaticUrl());
            }
        }
        ResponseResult responseResult = ResponseResult.okResult(apArticles);
        responseResult.setHost(webSite);
        return responseResult;
    }
}