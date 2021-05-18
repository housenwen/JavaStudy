package com.tanhua.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SettingsVo {

    private Long id;
    //陌生人问题
    private String strangerQuestion = "";
    //手机号
    private String phone;
    //推送喜欢通知
    private Boolean likeNotification = true;
    //推送评论通知
    private Boolean pinglunNotification = true;
    //推送公告通知
    private Boolean gonggaoNotification = true;

}
