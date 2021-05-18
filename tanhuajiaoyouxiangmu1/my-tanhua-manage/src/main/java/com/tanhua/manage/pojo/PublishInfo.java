package com.tanhua.manage.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishInfo extends BasePojo {

    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 发布内容Id
     */
    private String publishId;
    /**
     * 置顶状态，1为未置顶，2为置顶
     */
    private Integer topState;
    /**
     * 审核状态，1为待审核，2为自动审核通过，3为待人工审核，4为人工审核拒绝，5为人工审核通过，6为自动审核拒绝
     */
    private String state;
    /**
     * 喜欢数
     */
    private Integer loveCount;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 举报数
     */
    private Integer reportCount;
    /**
     * 转发数
     */
    private Integer forwardingCount;
    /**
     * 发布时间
     */
    private Long createDate;
}