package com.heima.model.admin.dto;

import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @作者 itcast
 * @创建日期 2021/5/22 14:35
 **/
@Data
public class ChannelDto extends PageRequestDto {
    @ApiModelProperty("频道名称")
    private String name;  // 频道名称
}
