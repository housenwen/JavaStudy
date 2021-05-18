package com.tanhua.manage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishAuditLog extends BasePojo {

    private Long id;
    /**
     * 发布内容Id
     */
    private String publishId;

    /**
     * 原审核状态，1为待审核，2为自动审核通过，3为待人工审核，4为人工审核拒绝，5为人工审核通过
     */
    private String sourceState;

    /**
     * 目标审核状态，1为待审核，2为自动审核通过，3为待人工审核，4为人工审核拒绝，5为人工审核通过
     */
    private String targetState;
}