package com.heima.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.model.article.dto.ArticleDto;
import com.heima.model.article.dto.ArticleHomeDto;
import com.heima.model.article.dto.ArticleInfoDto;
import com.heima.model.article.mess.ArticleVisitStreamMess;
import com.heima.model.article.pojo.ApArticle;
import com.heima.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {
    ResponseResult saveApArticle(ArticleDto articleDto);

    ResponseResult load(Short loadType, ArticleHomeDto dto);

    /**
     * 根据参数加载文章列表  v2
     * @param loadtypeLoadMore
     * @param dto
     * @param firstPage
     * @return
     */
    public ResponseResult load2(Short loadtypeLoadMore, ArticleHomeDto dto,boolean firstPage);

    ResponseResult loadArticleInfo(ArticleInfoDto dto);

    ResponseResult loadArticleBehavior(ArticleInfoDto dto);

    void updateApArticle(ArticleVisitStreamMess mess);
}