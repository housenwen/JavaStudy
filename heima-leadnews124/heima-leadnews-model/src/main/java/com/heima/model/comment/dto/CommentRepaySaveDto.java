package com.heima.model.comment.dto;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class CommentRepaySaveDto {
    /**
     * 评论id
     */
    @NotNull(message = "评论id不能为空")
    private String commentId;
    /**
     * 回复内容
     */
    @NotBlank(message = "评论内容不能为空")
    @Length(max = 140,message = "评论内容不能大于140个字")
    private String content;
}