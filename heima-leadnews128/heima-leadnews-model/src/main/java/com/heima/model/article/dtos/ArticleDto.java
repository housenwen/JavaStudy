package com.heima.model.article.dtos;
import com.heima.model.article.pojos.ApArticle;
import lombok.Data;
@Data
public class ArticleDto extends ApArticle {
    /**
     * 文章内容
     */
    private String content;
    /**
     * 自媒体用户id
     */
    private Integer wmUserId;
}