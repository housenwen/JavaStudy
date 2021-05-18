package com.tanhua.manage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserFreeze extends BasePojo{
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

}
