package com.heima.model.wemedia.dto;
import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;
@Data
public class NewsAuthDto extends PageRequestDto {
    /**
     * 文章标题
     */
    private String title;
    /**
     * 状态
     */
    private Short status;
    /**
     * 文章id
     */
    private Integer id;
    /**
     * 失败原因
     */
    private String msg;
}