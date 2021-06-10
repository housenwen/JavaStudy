package com.heima.model.comment.dto;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
@Data
public class CommentRepayLikeDto {
    @NotNull(message = "评论回复ID不能为空")
    private String commentRepayId;
    /**
     * 0：点赞
     * 1：取消点赞
     */
    @Range(min = 0,max = 1,message = "点赞参数错误")
    private Short operation;
}