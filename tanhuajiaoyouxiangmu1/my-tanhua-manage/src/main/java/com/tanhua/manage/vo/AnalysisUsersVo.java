package com.tanhua.manage.vo;

import lombok.Data;

import java.util.List;
/*
* 分析关于用户的数据
* */
@Data
public class AnalysisUsersVo {
    /**
     * 本年
     */
    private List<DataPointVo> thisYear;
    /**
     * 去年
     */
    private List<DataPointVo> lastYear;
}
