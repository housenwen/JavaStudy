package com.tanhua.dubbo.enums;

/**
 * 消息类型；txt:文本消息，img：图片消息，loc：位置消息，audio：语音消息，video：视频消息，file：文件消息
 */
public enum HuanXinMessageType {

    TXT("txt"), IMG("img"), LOC("loc"), AUDIO("audio"), VIDEO("video"), FILE("file");

    String type;

    HuanXinMessageType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}