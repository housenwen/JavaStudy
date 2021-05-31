package com.heima.model.media.dtos;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.util.Date;

@Data
public class WmNewsPageReqDto extends PageRequestDto {

    private Short status;//状态
    private Date beginPubdate;//开始时间
    private Date endPubdate;//结束时间
    private Integer channelId;//所属频道ID
    private String keyWord;//关键字
}