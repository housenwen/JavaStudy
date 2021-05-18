package com.tanhua.server.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageCommentVo {

    private String id;
    private String avatar;
    private String nickname;
    private String createDate; //格式：2019-09-08 10:07

}