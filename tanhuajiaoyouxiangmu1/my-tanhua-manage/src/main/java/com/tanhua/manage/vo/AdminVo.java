package com.tanhua.manage.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminVo {
    private Long id;
    private String username;
    private String avatar;
    private String token;
    private String password;
    private String uuid;
    private String verificationCode;
}