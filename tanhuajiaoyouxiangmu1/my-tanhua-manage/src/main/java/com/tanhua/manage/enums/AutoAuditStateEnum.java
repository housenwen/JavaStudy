package com.tanhua.manage.enums;



/*
* 进行审核
* */
public enum AutoAuditStateEnum {
    // 审核状态，2为自动审核通过，3为待人工审核，，6为自动审核拒绝
    PASS("2", "pass"),
    REVIEW("3", "review"),
    BLOCK("6", "block"),
    ;

    private String value;
    private String name;

    AutoAuditStateEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static String getValueByName(String name) {
        String value = "3";
        for (AutoAuditStateEnum e : values()) {
            if (e.name.equals(name)) {
                value = e.value;
            }
        }
        return value;
    }
}