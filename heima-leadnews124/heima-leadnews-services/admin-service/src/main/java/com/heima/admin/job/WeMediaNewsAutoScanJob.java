package com.heima.admin.job;

import com.heima.admin.service.WemediaNewsAutoScanService;
import com.heima.feigns.wemedia.WemediaFeign;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/6/2 9:14
 **/
@Component
@Slf4j
public class WeMediaNewsAutoScanJob {

    @Autowired
    WemediaFeign wemediaFeign;

    @Autowired
    WemediaNewsAutoScanService wemediaNewsAutoScanService;

    /**
     * 定时发布文章的任务
     * @param params
     * @return
     */
    @XxlJob("wemediaAutoScanJob")
    public ReturnT wemediaAutoScanJob(String params){
        log.info("========================定时发布文章任务被触发============================");
        // 1. 远程调用自媒体查询待发布文章
        List<Integer> releaseIds = wemediaFeign.findRelease();
        if(releaseIds!=null && releaseIds.size()>0){
            // 2. 调用自动发布访问  发布文章
            releaseIds.forEach(wemediaNewsAutoScanService::autoScanByMediaNewsId);
        }
        return ReturnT.SUCCESS; // 任务成功
    }



}
