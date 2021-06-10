package com.heima.model.article.vo;
import com.heima.model.article.pojo.ApArticle;
import lombok.Data;
@Data
public class HotArticleVo extends ApArticle {
    /**
     * 分值
     */
    private Integer score;
}