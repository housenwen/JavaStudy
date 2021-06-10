package com.heima.behavior.service;

import com.heima.model.behavior.dto.ApArticleRelationDto;

import java.util.Map;

public interface ApArticleRelationService {
    /**
     * 查询用户文章的点赞\不喜欢
     */
    public Map findApArticleRelation(ApArticleRelationDto dto);
}