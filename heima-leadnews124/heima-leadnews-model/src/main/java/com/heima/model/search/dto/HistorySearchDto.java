package com.heima.model.search.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HistorySearchDto {

    // 设备ID
    Integer equipmentId;
    /**
     * 接收搜索历史记录id
     */
    @NotNull(message = "历史记录id不能为空")
    String id;
}