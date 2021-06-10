package com.heima.model.article.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CollectionBehaviorDto {
    // 设备ID
    Integer equipmentId;
    // 文章、动态ID
    Long entryId;
    /**
     * 收藏内容类型
     * 0文章
     * 1动态
     */
    Short type;
    /**
     * 操作类型
     * 0收藏
     * 1取消收藏
     */
    Short operation;
    Date publishedTime;
}