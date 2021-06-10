package com.heima.model.user.dto;
import lombok.Data;
@Data
public class UserRelationDto {
    // 文章作者ID
    Integer authorId;
    // 文章id
    Long articleId;
    /**
     * 操作方式
     * 0  关注
     * 1  取消
     */
    Short operation;
}