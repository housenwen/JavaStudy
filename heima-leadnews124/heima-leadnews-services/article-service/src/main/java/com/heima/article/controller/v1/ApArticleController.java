package com.heima.article.controller.v1;
import com.heima.article.service.ApArticleService;
import com.heima.model.article.dto.ArticleDto;
import com.heima.model.article.pojo.ApArticle;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.vo.SearchArticleVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "App端文章API",tags = "App端文章API")
@RestController
@RequestMapping("/api/v1/article")
public class ApArticleController{
    @Autowired
    ApArticleService apArticleService;

    @ApiOperation(value = "保存App端文章信息",notes = "基于ArticleDto 保存文章信息 文章详情信息 文章配置信息,最后将生成的文章id返回")
    @PostMapping("/save")
    public ResponseResult saveArticle(@RequestBody ArticleDto articleDto) {
        return apArticleService.saveApArticle(articleDto);
    }

    @ApiOperation("根据ID查询文章搜索VO")
    @GetMapping("{id}")
    public SearchArticleVo findArticle(@PathVariable Long id) {
        ApArticle article = apArticleService.getById(id);
        if(article!=null){
            SearchArticleVo searchArticleVo = new SearchArticleVo();
            BeanUtils.copyProperties(article,searchArticleVo);
            return searchArticleVo;
        }
        return null;
    }
}