package com.heima.model.comment.dto;
import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class CommentDto  extends PageRequestDto {
	@NotNull(message="文章id不能为空")
    private Long articleId;
    // 最小时间
    private Date minDate;
    //是否是首页
    private Short index;
}