package com.heima.article.job;

import com.heima.article.service.HotArticleService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @作者 itcast
 * @创建日期 2021/6/9 14:16
 **/
@Component
@Slf4j
public class ComputeHotArticleJob {
    @Autowired
    HotArticleService hotArticleService;
    @XxlJob("computeHotArticleJob")
    public ReturnT computeHotArticleJob(String params){
        log.info("计算热点文章的定时任务被触发 ======================");
        hotArticleService.computeHotArticle();
        log.info("计算热点文章的定时任务执行完毕 ======================");
        return ReturnT.SUCCESS;
    }
}
