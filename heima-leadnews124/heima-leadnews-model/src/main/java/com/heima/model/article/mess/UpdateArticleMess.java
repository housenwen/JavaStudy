package com.heima.model.article.mess;
import lombok.Data;
@Data
public class UpdateArticleMess {
    /**
     * 修改文章的字段类型
      */
    private UpdateArticleType type;
    /**
     * 文章ID
     */
    private Long articleId;
    /**
     * 数值 用于 + - 热度
     */
    private Integer add;
    
    public enum UpdateArticleType{
        COLLECTION,COMMENT,LIKES,VIEWS;
    }
}