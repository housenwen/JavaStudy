package com.tanhua.dubbo.pojo;

import com.tanhua.dubbo.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo extends BasePojo {

    private static final long serialVersionUID = -7814043583142105146L;
    //有默认值
    private Long id;
    //有默认值
    private Long userId; //用户id
    //有默认值
    private String nickName; //昵称
    //有默认值
    private String logo; //用户头像
    //标签没有默认值
    private String tags; //用户标签：多个用逗号分隔
    //有默认值
    private SexEnum sex; //性别
    //通过计算得到
    private Integer age; //年龄
    //默认本科
    private String edu; //学历
    private String city; //城市
    private String birthday; //生日
    //没有默认值?
    private String coverPic; // 封面图片
    //没有默认值
    private String industry; //行业
    //没有默认值
    private String income; //收入
    //没有默认值
    private String marriage; //婚姻状态

}
