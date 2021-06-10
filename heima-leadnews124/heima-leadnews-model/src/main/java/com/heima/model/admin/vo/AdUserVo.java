package com.heima.model.admin.vo;
import lombok.Data;
import java.util.Date;
@Data
public class AdUserVo {
    private Integer id;
    private String name;
    private String nickname;
    private String image;
    private String phone;
    private Integer status;
    private String email;
    private Date loginTime;
    private Date createdTime;
}