package com.heima.admin.job;

import com.heima.admin.service.WemediaNewsAutoScanService;
import com.heima.feigns.WemediaFeign;
import com.heima.model.common.dtos.ResponseResult;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @作者 itcast
 * @创建日期 2021/7/12 14:24
 **/
@Component
@Slf4j
public class WeMediaNewsAutoScanJob {
    @Autowired
    WemediaFeign wemediaFeign;
    @Autowired
    WemediaNewsAutoScanService wemediaNewsAutoScanService;
    @XxlJob("wemediaAutoScanJob")
    public ReturnT autoScanJob(String params){
        log.info(" =========================自动发布文章任务触发 start===============================");
        ResponseResult<List<Integer>> release = wemediaFeign.findRelease();
        if(release.getCode().intValue()!=0){
            log.error(" 远程调用待文章接口 失败  原因:{}",release.getErrorMessage());
            return ReturnT.FAIL;
        }
        List<Integer> newsIds = release.getData();
        log.info("需要发布的文章id ==> {}",newsIds);
        if(newsIds!=null && newsIds.size()>0){
            for (Integer newsId : newsIds) { // 发布
                wemediaNewsAutoScanService.autoScanByMediaNewsId(newsId);
            }
        }
        log.info(" =========================自动发布文章任务结束 end===============================");
        return ReturnT.SUCCESS;
    }
}
