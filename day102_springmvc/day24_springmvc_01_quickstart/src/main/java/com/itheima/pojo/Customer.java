package com.itheima.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data //自动生成get和set方法
public class Customer {

    /**
     * 属性名和表单的key一致
     */
    private String custId;
    private String custName;
    private String custSource;
    private String custIndustry;
    private String custLevel;
    private String custAddress;
    private String custPhone;
    private Date createrTime;

    //客户的附属信息
    private CustomerInfo customerInfo;


}
