package com.tanhua.manage.vo;

import lombok.Data;

import java.util.List;

@Data
public class MovementsVo {
    /**
     * 编号
     */
    private String id;
    /**
     * 作者昵称
     */
    private String nickname;
    /**
     * 作者ID
     */
    private Long userId;
    /**
     * 作者头像
     */
    private String userLogo;
    /**
     * 发布日期
     */
    private Long createDate;
    /**
     * 正文
     */
    private String text;
    /**
     * 审核状态，1为待审核，2为自动审核通过，3为待人工审核，4为人工审核拒绝，5为人工审核通过，6为自动审核拒绝
     */
    private String state;
    /**
     * 举报数
     */
    private Integer reportCount;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 转发数
     */
    private Integer forwardingCount;
    /**
     * 图片列表
     */
    private List<String> medias;
    /**
     * 置顶状态，1为未置顶，2为置顶
     */
    private Integer topState;
}