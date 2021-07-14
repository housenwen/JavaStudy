package com.heima.article.controller.v1;
import com.heima.article.service.ApArticleService;
import com.heima.model.article.dtos.ArticleDto;
import com.heima.model.common.dtos.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "app文章管理API",tags = "app文章管理API")
@RestController
@RequestMapping("/api/v1/article")
public class ApArticleController {
    @Autowired
    ApArticleService apArticleService;
    @ApiOperation(value = "保存app文章",notes = "保存 app_article信息,ap_article_config信息,ap_article_content信息")
    @PostMapping("/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto articleDto) {
        return apArticleService.saveArticle(articleDto);
    }
}