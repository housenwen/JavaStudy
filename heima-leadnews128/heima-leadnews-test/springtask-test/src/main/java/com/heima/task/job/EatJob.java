package com.heima.task.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @作者 itcast
 * @创建日期 2021/7/12 10:16
 **/
@Component
public class EatJob {
    /**
     * 定义当前方法，是一个调度任务  cron=时间表达
     */
    @Scheduled(cron = "*/3 * * * * ?")
    public void eat(){
        System.out.println("每隔3S  吃饭 干饭人，干饭魂   "+ LocalDateTime.now().toString());
    }
}
