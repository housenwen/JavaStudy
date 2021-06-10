package com.heima.model.behavior.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * APP阅读行为表
 * @author itheima
 */
@Data
@TableName("ap_read_behavior")
public class ApReadBehavior implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.ID_WORKER)
    private Long id;
    /**
     * 用户ID
     */
    @TableField("entry_id")
    private Integer entryId;
    /**
     * 文章ID
     */
    @TableField("article_id")
    private Long articleId;
    /**
     * 阅读次数
     */
    @TableField("count")
    private Short count;
    /**
     * 阅读时间单位秒
     */
    @TableField("read_duration")
    private Integer readDuration;
    /**
     * 阅读百分比
     */
    @TableField("percentage")
    private Short percentage;
    /**
     * 文章加载时间
     */
    @TableField("load_duration")
    private Short loadDuration;
    /**
     * 登录时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 更新时间
     */
    @TableField("updated_time")
    private Date updatedTime;
}