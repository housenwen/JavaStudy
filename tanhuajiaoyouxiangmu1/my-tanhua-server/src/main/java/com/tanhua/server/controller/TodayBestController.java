package com.tanhua.server.controller;

import com.tanhua.common.utils.Cache;
import com.tanhua.server.service.TodayBestService;
import com.tanhua.server.vo.PageResult;
import com.tanhua.server.vo.RecommendUserQueryParam;
import com.tanhua.server.vo.TodayBest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("tanhua")
public class TodayBestController {

    @Autowired
    private TodayBestService todayBestService;

    /**
     * 查询今日佳人
     *
     * @return
     */
    @GetMapping("todayBest")
    @Cache(time = "90") //开启缓存，默认60秒
    public TodayBest queryTodayBest() {
        return this.todayBestService.queryTodayBest();
    }

    /**
     * 查询推荐列表
     *
     * @param queryParam
     * @return
     */
    @GetMapping("recommendation")
    @Cache
    public PageResult queryRecommendList(RecommendUserQueryParam queryParam) {
        return this.todayBestService.queryRecommendList(queryParam);
    }

}
