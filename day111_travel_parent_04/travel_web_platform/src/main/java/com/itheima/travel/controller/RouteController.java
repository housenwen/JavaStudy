package com.itheima.travel.controller;


import com.github.pagehelper.PageInfo;
import com.itheima.travel.enums.StatusEnum;
import com.itheima.travel.exception.ProjectException;
import com.itheima.travel.req.RouteVo;
import com.itheima.travel.req.UserVo;
import com.itheima.travel.res.ResponseWrap;
import com.itheima.travel.service.RouteService;
import com.itheima.travel.utils.ExceptionsUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RequestMapping("/route")
@RestController
@Log4j2
@CrossOrigin
public class RouteController {

    @Autowired
    private RouteService routeService;


    @Value("${webSite}")
    private String webSite;

    @PostMapping("/favoriteRankByPageAndCondition")
    public ResponseWrap<PageInfo<RouteVo>> favoriteRankByPageAndCondition(@RequestBody RouteVo routeVo) throws ProjectException {

        try {
            PageInfo<RouteVo> voPageInfo = routeService.favoriteRankByPageAndCondition(routeVo);
            return ResponseWrap.<PageInfo<RouteVo>>builder()
                    .operationTime(new Date())
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .data(voPageInfo)
                    .webSite(webSite)
                    .build();
        } catch (Exception e) {
            log.error("收藏排行榜的按条件分页查询：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(StatusEnum.FIND_ROUTE_PAGE_FAIL.getCode(),
                    StatusEnum.FIND_ROUTE_PAGE_FAIL.getMsg());
        }
    }


    @GetMapping("/findRouteDetailsByRouteId")
    public ResponseWrap<RouteVo> findRouteDetailsByRouteId(@RequestBody RouteVo routeVo) throws ProjectException {

        try {
            RouteVo routeVo1 = routeService.findRouteDetailsByRid(routeVo);
            return ResponseWrap.<RouteVo>builder()
                    .operationTime(new Date())
                    .code(StatusEnum.SUCCEED.getCode())
                    .msg(StatusEnum.SUCCEED.getMsg())
                    .data(routeVo1)
                    .build();
        } catch (Exception e) {
            log.error("收藏线路详情：{}", ExceptionsUtil.getStackTraceAsString(e));
            throw new ProjectException(StatusEnum.FAIL.getCode(),
                    StatusEnum.FAIL.getMsg());
        }

    }
}
