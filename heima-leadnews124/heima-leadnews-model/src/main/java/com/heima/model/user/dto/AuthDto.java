package com.heima.model.user.dto;

import com.heima.model.common.dtos.PageRequestDto;
import lombok.Data;

@Data
public class AuthDto extends PageRequestDto {
    // 认证用户ID
    private Integer id;
    //驳回的信息
    private String msg;
    //状态
    private Short status;
}