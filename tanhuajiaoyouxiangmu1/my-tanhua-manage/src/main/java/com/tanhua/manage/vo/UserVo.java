package com.tanhua.manage.vo;

import lombok.Data;

@Data
public class UserVo {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 账号状态
     */
    private String userStatus;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 用户头像
     */
    private String logo;
    /**
     * 用户标签
     */
    private String tags;
    /**
     * 用户性别
     */
    private String sex;
    /**
     * 学历
     */
    private String edu;
    /**
     * 所在城市
     */
    private String city;
    /**
     * 生日
     */
    private String birthday;
    /**
     * 封面图片
     */
    private String coverPic;
    /**
     * 行业
     */
    private String industry;
    /**
     * 收入
     */
    private String income;
    /**
     * 婚姻状况
     */
    private String marriage;
    /**
     * 注册时间
     */
    private Long created;
    /**
     * 被喜欢人数
     */
    private Long countBeLiked;
    /**
     * 喜欢人数
     */
    private Long countLiked;
    /**
     * 配对人数
     */
    private Long countMatching;
    /**
     * 最近活跃时间
     */
    private Long lastActiveTime;
    /**
     * 最近登录地
     */
    private String lastLoginLocation;
    /**
     * 个性签名
     */
    private String personalSignature;
    /**
     * 年龄
     */
    private Integer age;
}