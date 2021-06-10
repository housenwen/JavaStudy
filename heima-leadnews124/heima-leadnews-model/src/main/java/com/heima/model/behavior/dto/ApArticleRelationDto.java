package com.heima.model.behavior.dto;

import lombok.Data;
@Data
public class ApArticleRelationDto {
    //文章id
    Long articleId;
    //当前登录用户id
    Integer entryId;
    /**
     * 实体类型
     *  0 终端设备
     *  1 用户
     */
    Short type;
}