package com.heima.model.comment.dto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CommentSaveDto {
    @NotNull(message = "文章id不能为空")
    private Long articleId;
    @Length(max = 140,message = "评论内容不能大于140个字符")
    @NotBlank(message = "评论内容不能为空")
    private String content;
}