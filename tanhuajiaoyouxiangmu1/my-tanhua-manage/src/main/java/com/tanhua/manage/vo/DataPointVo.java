package com.tanhua.manage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPointVo {
    /**
     * 数据点名称
     */
    private String title;
    /**
     * 数量
     */
    private Long amount;
}