package com.heima.model.comment.dto;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class CommentRepayDto {
    @NotNull(message = "评论id不能为空")
    private String commentId;
    private Integer size;
    // 最小时间
    private Date minDate;
}