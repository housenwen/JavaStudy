package com.tanhua.manage.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalVo {
    /**
     * 状态标题
     */
    private String title;
    /**
     * 状态代码
     */
    private String code;
    /**
     * 数量
     */
    private Long value;
}
