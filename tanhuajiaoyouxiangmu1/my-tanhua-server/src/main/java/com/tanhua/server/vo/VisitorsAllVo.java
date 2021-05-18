package com.tanhua.server.vo;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ClassName VisitorsAllVo
 * @Description
 * @Author 执鉴
 * @Date 2021/5/6 20:50
 * Version 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorsAllVo {
    @Alias("userId")
    private Long id;
    @Alias("logo")
    private String avatar;
    @Alias("nickName")
    private String nickname;
    private String gender;
    private Integer age;
    private String city;
    @Alias("edu")
    private String education;
    private Integer marriage;
    private Integer matchRate;
    private boolean alreadyLove;
}
