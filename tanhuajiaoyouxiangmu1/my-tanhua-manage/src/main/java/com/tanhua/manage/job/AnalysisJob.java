package com.tanhua.manage.job;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.tanhua.manage.enums.LogTypeEnum;
import com.tanhua.manage.pojo.AnalysisByDay;
import com.tanhua.manage.pojo.Log;
import com.tanhua.manage.pojo.User;
import com.tanhua.manage.service.AnalysisService;
import com.tanhua.manage.service.LogService;
import com.tanhua.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AnalysisJob {

    @Autowired
    private UserService userService;

    @Autowired
    private AnalysisService analysisService;

    @Autowired
    private LogService logService;

//    @Scheduled(cron = "0 0/1 * * * *") //每分执行，用于测试
    @Scheduled(cron = "0 0/30 * * * *") //每30分钟执行
    public void run() {
//        Date today = new Date();
//        String todayStr = DateUtil.formatDate(today);

        //测试
        Date today = DateUtil.parse("2020-09-08");
        String todayStr = "2020-09-08";

        //查询当天的统计对象
        AnalysisByDay analysisByDay = analysisService.getOne(Wrappers.<AnalysisByDay>lambdaQuery().eq(AnalysisByDay::getRecordDate, todayStr));

        if (analysisByDay == null) {
            analysisByDay = new AnalysisByDay();
            analysisByDay.setRecordDate(today);
            this.analysisService.save(analysisByDay);
        }

        //查询当日的活跃数
        int activeCount = this.logService.count(Wrappers.query(Log.builder().logTime(todayStr).build())
                .select("DISTINCT(user_id)"));
        if (activeCount > 0) {
            analysisByDay.setNumActive(activeCount);
        }

        //查询用户登录数据
        List<Log> logList = this.logService.list(Wrappers.query(Log.builder()
                .logTime(todayStr)
                .type(LogTypeEnum.LOGIN.getValue())
                .build()).select("DISTINCT(user_id)")
        );

        if (CollUtil.isNotEmpty(logList)) {
            //今日登录用户
            analysisByDay.setNumLogin(logList.size());

            //统计数据归0，重新计算
            analysisByDay.setNumRegistered(0);
            analysisByDay.setNumRetention1d(0);

            List<User> userList = this.userService.list(Wrappers.<User>lambdaQuery().in(User::getId, CollUtil.getFieldValues(logList, "userId")));
            for (User user : userList) {
                Long days = DateUtil.betweenDay(user.getCreated(), today, true);
                if (days == 0) {
                    //今日注册
                    analysisByDay.setNumRegistered(analysisByDay.getNumRegistered() + 1);
                } else if (days == 1) {
                    //次日留存
                    analysisByDay.setNumRetention1d(analysisByDay.getNumRetention1d() + 1);
                }
            }
        }

        //更新数据
        this.analysisService.updateById(analysisByDay);

    }
}
