package com.tanhua.manage.enums;

public enum PublishAuditStateEnum {
    // 审核状态，1为待审核，2为自动审核通过，3为待人工审核，4为人工审核拒绝，5为人工审核通过，6为自动审核拒绝
    WAIT("1", "待审核"),
    AUTO_PASS("2", "自动审核通过"),
    WAIT_MAUL("3", "待人工审核"),
    MAUL_BLOCK("4", "人工审核拒绝"),
    MAUL_PASS("5", "人工审核通过"),
    AUTO_BLOCK("6", "自动审核拒绝"),
    ;

    private String value;
    private String name;

    PublishAuditStateEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;	
    }

    public String getName() {
        return name;
    }
}