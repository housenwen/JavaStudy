package com.heima.feigns.article;

import com.heima.config.HeimaFeignAutoConfiguration;
import com.heima.model.article.dto.ArticleDto;
import com.heima.model.article.pojo.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.vo.SearchArticleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "leadnews-article",configuration = HeimaFeignAutoConfiguration.class)
public interface ArticleFeign {
    @GetMapping("/api/v1/author/findByUserId/{userId}")
    public ApAuthor findByUserId(@PathVariable("userId") Integer userId);
    @PostMapping("/api/v1/author/save")
    public ResponseResult save(@RequestBody ApAuthor apAuthor);

    @GetMapping("/api/v1/author/one/{id}")
    ApAuthor selectById(@PathVariable("id") Integer id);

    @PostMapping("/api/v1/article/save")
    ResponseResult saveArticle(@RequestBody ArticleDto articleDto);


    @GetMapping("/api/v1/article/{id}")
    SearchArticleVo findArticle(@PathVariable("id") Long id);
}