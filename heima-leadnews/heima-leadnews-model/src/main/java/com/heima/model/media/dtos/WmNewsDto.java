package com.heima.model.media.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class WmNewsDto {
    private Integer id;
    private String title;//标题
    private Integer channelId;//频道id
    private String labels;//标签
    private Date publishTime;//发布时间
    private String content;//文章内容
    private Short type;//文章封面类型  0 无图 1 单图 3 多图 -1 自动
    private Short enable;//是否上架  0 下架  1 上架
    private Date submitedTime; //提交时间
    private Short status;//状态 提交为1  草稿为0
    private String reason; 
    private List<String> images;//封面图片列表 多张图以逗号隔开
}