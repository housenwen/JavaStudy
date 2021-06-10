package com.heima.search.service;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.search.dto.UserSearchDto;
import com.heima.model.search.vo.SearchArticleVo;

public interface ArticleSearchService {
    /**
     ES文章分页搜索
     @return
     */
    ResponseResult search(UserSearchDto userSearchDto);

    void saveArticle(SearchArticleVo article);

    void delete(Long articleId);

}