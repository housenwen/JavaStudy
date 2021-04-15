package com.itheima.travel.res;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.itheima.travel.utils.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description 返回结果
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWrap<T> extends ToString {

    //返回编码
    private String code;

    //返回信息
    private String msg;

    //创建时间,处理json的时间参数解析
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss",timezone = "GMT+8")
    private Date operationTime;

    //返回结果
    private T data;

    //图片站点
    private String webSite;

}
