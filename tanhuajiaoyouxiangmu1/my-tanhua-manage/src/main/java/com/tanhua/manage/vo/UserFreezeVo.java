package com.tanhua.manage.vo;

import lombok.Data;

@Data
public class UserFreezeVo {
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 冻结时间，1为冻结3天，2为冻结7天，3为永久冻结
     */
    private Integer freezingTime;
    /**
     * 冻结范围，1为冻结登录，2为冻结发言，3为冻结发布动态
     */
    private Integer freezingRange;
    /**
     * 冻结原因
     */
    private String reasonsForFreezing;
    /**
     * 备注
     */
    private String frozenRemarks;
    /**
     * 解冻原因
     */
    private String reasonsForThawing;
}