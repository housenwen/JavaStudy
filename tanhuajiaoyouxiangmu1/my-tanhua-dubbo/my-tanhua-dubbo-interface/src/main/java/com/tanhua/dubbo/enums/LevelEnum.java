package com.tanhua.dubbo.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum LevelEnum implements IEnum<Integer> {

    PRIMARY(1, "初级"), MIDDLE(2, "中级"), SENIOR(3, "高级");

    private int value;
    private String desc;

    LevelEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.desc;
    }
}
