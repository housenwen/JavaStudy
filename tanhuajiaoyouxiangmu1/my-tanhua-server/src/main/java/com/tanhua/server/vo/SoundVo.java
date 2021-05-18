package com.tanhua.server.vo;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoundVo {
    @Alias("userId")
    private Integer id;//用户id
    @Alias("logo")
    private String avatar;
    @Alias("nickName")
    private String nickname;
    private String gender;//性别 man woman
    private Integer age;
    private String soundUrl;
    private Integer remainingTimes;//剩余次数
}
