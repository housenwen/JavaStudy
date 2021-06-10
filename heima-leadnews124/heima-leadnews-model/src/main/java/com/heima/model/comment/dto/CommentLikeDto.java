package com.heima.model.comment.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Data
public class CommentLikeDto {
    @NotBlank(message = "评论ID不能为空")
    private String commentId;
    @Range(min = 0,max = 1,message = "评论点赞操作状态错误")
    private Short operation;
}