package com.itheima.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
//实体类和表建立映射关系
@TableName(value = "tb_user")
public class User implements Serializable {

    // 主键字段名称 id   主键自动增长
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    // 用户名  如果 属性名和数据库字段名一致   该注解可以省略
    @TableField(value = "username")
    private String userName;

    // 密码
    private String password;

    // 姓名
    private String name;

    // 年龄
    private Integer age;

    // 性别，1男性，2女性
    private Integer sex;

    // 出生日期
    private String birthday;

}