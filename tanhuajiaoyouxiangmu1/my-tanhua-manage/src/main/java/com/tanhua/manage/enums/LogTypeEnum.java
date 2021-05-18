package com.tanhua.manage.enums;
/*
* 操作类型,\r\n
* 01为登录
* ，0201为发动态，
* 0202为浏览动态，0203为动态点赞，
* 0204为动态喜欢，0205为评论，
* 0206为动态取消点赞，
* 0207为动态取消喜欢，
* 0301为发小视频，
* 0302为小视频点赞
* ，0303为小视频取消点赞，
* 0304为小视频评论',*/
public enum LogTypeEnum {
    LOGIN("01","登录"),
    MOVEMENTS_ADD("0201","发动态"),
    MOVEMENTS_LIKE("0203","动态点赞"),
    MOVEMENTS_LOVE("0204","动态喜欢"),
    MOVEMENTS_COMMENT("0304","小视频评论"),
    MOVEMENTS_UNLIKE("0206","取消点赞"),
    MOVEMENTS_UNLOVE("0207","取消喜欢");

    private String value;
    private String des;

    LogTypeEnum(String value, String des) {
        this.value = value;
        this.des = des;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
