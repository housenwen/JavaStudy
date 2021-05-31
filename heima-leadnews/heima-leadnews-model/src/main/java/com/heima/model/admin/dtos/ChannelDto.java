package com.heima.model.admin.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChannelDto extends PageRequestDto {

    /**
     * 频道名称
     */
    @ApiModelProperty("频道名称")
    private String name;
}