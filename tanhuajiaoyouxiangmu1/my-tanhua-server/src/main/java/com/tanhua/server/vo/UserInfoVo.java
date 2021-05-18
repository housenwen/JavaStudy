package com.tanhua.server.vo;

import cn.hutool.core.annotation.Alias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {

    @Alias("userId")
    private Long id; //用户id
    @Alias("logo")
    private String avatar; //头像
    @Alias("nickName")
    private String nickname; //昵称
    private String birthday; //生日 2019-09-11
    private String age; //年龄
    private String gender; //性别 man woman
    private String city; //城市
    @Alias("edu")
    private String education; //学历
    private String income; //月收入
    @Alias("industry")
    private String profession; //行业
    private Integer marriage; //婚姻状态（0未婚，1已婚）

}
