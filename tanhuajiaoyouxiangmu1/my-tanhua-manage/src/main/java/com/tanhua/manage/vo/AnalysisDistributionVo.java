package com.tanhua.manage.vo;

import lombok.Data;

import java.util.List;

@Data
public class AnalysisDistributionVo {
    /**
     * 行业分布TOP10
     */
    private List<DataPointVo> industryDistribution;
    /**
     * 年龄分布
     */
    private List<DataPointVo> ageDistribution;
    /**
     * 性别分布
     */
    private List<DataPointVo> genderDistribution;
    /**
     * 地区分布
     */
    private List<DataPointVo> localDistribution;
    /**
     * 地区合计
     */
    private List<DataPointVo> localTotal;
}
