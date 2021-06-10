package com.heima.model.behavior.dto;

import lombok.Data;

@Data
public class UnLikesBehaviorDto {
    // 设备ID
    Integer equipmentId;
    // 文章ID
    Long articleId;
    /**
     * 不喜欢操作方式
     * 0 不喜欢
     * 1 取消不喜欢
     */
    Short type;

}