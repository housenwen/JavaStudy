package com.itheima.travel.service;

import com.github.pagehelper.PageInfo;
import com.itheima.travel.req.RouteVo;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RouteServiceTest extends BasicTest {

    @Test
    public void findRouteDetailsByRid() {


        RouteVo routeVo = RouteVo.builder()
                .id(1l)
                .build();

        RouteVo routeVo1 = routeService.findRouteDetailsByRid(routeVo);

        System.out.println(routeVo1);
    }


    @Test
    public void favoriteRankByPageAndCondition(){

        RouteVo routeVo = RouteVo.builder()
                .minPrice(new BigDecimal(900))
                .maxPrice(new BigDecimal(10000))
                .pageNum(1)
                .pageSize(3)
                .build();

        PageInfo<RouteVo> pageInfo = routeService.favoriteRankByPageAndCondition(routeVo);
        System.out.println(pageInfo);
    }
}