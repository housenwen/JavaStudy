package com.tanhua.manage.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.tanhua.manage.service.AnalysisService;
import com.tanhua.manage.service.UserService;
import com.tanhua.manage.util.ComputeUtil;
import com.tanhua.manage.util.NoAuthorization;
import com.tanhua.manage.vo.AnalysisDistributionVo;
import com.tanhua.manage.vo.AnalysisSummaryVo;
import com.tanhua.manage.vo.AnalysisUsersVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AnalysisController {

    @Autowired
    private UserService userService;
    @Autowired
    private AnalysisService analysisService;

/**
     * 概要统计信息
     *
     * @return*/


    @GetMapping("/dashboard/summary")
    @NoAuthorization
    public AnalysisSummaryVo getSummary() {
        AnalysisSummaryVo analysisSummaryVo = new AnalysisSummaryVo();
//        DateTime dateTime = DateUtil.date();
        DateTime dateTime = DateUtil.parseDate("2020-09-08");

        //累计用户数
        analysisSummaryVo.setCumulativeUsers(Long.valueOf(this.userService.count()));

        //过去30天活跃用户
        analysisSummaryVo.setActivePassMonth(this.analysisService.queryActiveUserCount(dateTime, -30));

        //过去7天活跃用户
        analysisSummaryVo.setActivePassWeek(this.analysisService.queryActiveUserCount(dateTime, -7));

        //今日活跃用户
        analysisSummaryVo.setActiveUsersToday(this.analysisService.queryActiveUserCount(dateTime, 0));

        //昨日活跃用户
        analysisSummaryVo.setActiveUsersYesterday(this.analysisService.queryActiveUserCount(dateTime, -1));

        //今日新增用户
        analysisSummaryVo.setNewUsersToday(this.analysisService.queryRegisterUserCount(dateTime, 0));

        //今日新增用户涨跌率，单位百分数，正数为涨，负数为跌
        analysisSummaryVo.setNewUsersTodayRate(ComputeUtil.computeRate(
                analysisSummaryVo.getNewUsersToday(),
                this.analysisService.queryRegisterUserCount(dateTime, -1)
        ));

        //今日登录次数
        analysisSummaryVo.setLoginTimesToday(this.analysisService.queryLoginUserCount(dateTime, 0));

        //今日登录次数涨跌率，单位百分数，正数为涨，负数为跌
        analysisSummaryVo.setLoginTimesTodayRate(ComputeUtil.computeRate(
                analysisSummaryVo.getLoginTimesToday(),
                this.analysisService.queryLoginUserCount(dateTime, -1)
        ));

        //今日活跃用户涨跌率，单位百分数，正数为涨，负数为跌
        analysisSummaryVo.setActiveUsersTodayRate(ComputeUtil.computeRate(
                analysisSummaryVo.getActiveUsersToday(),
                analysisSummaryVo.getActiveUsersYesterday()
        ));

        //过去7天平均日使用时长，单位秒，没有数据，随机生成
        analysisSummaryVo.setUseTimePassWeek(RandomUtil.randomLong(600));

        //昨日活跃用户涨跌率，单位百分数，正数为涨，负数为跌
        analysisSummaryVo.setActiveUsersYesterdayRate(ComputeUtil.computeRate(
                analysisSummaryVo.getActiveUsersYesterday(),
                this.analysisService.queryActiveUserCount(dateTime, -2)
        ));

        return analysisSummaryVo;

    }

    /**
     * 新增、活跃用户、次日留存率
     *
     * @param sd   开始时间
     * @param ed   结束时间
     * @param type 101 新增 102 活跃用户 103 次日留存率
     * @return 新增 or 活跃用户 or 次日留存率
     */
    @NoAuthorization
    @GetMapping("/dashboard/users")
    public AnalysisUsersVo getUsers(@RequestParam(name = "sd") Long sd
            , @RequestParam("ed") Long ed
            , @RequestParam("type") Integer type) {
        return this.analysisService.queryAnalysisUsersVo(sd, ed, type);
    }


    /**
     * 注册用户分布，行业top、年龄、性别、地区
     *
     * @param sd 开始时间
     * @param ed   结束时间
     * @return 注册用户分布，行业top、年龄、性别、地区
     */
    @NoAuthorization
    @GetMapping("/dashboard/distribution")
    public AnalysisDistributionVo queryUserDistribution(@RequestParam(name = "sd") Long sd
            , @RequestParam("ed") Long ed) {
        return this.analysisService.queryUserDistribution(sd, ed);
    }
}
