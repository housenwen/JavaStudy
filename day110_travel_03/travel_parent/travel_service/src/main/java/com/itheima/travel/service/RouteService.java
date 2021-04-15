package com.itheima.travel.service;

import com.github.pagehelper.PageInfo;
import com.itheima.travel.req.RouteVo;

public interface RouteService {


    /**
     *  根据旅游线路的id查询旅游线路的详情：
     *      详情：tab_category,tab_route,tab_affix,tab_seller
     *
     */
    public RouteVo findRouteDetailsByRid(RouteVo routeVo);


    /**
     *
     * @param routeVo
     *  包含了分页查询的数据，
     *  pageNum,
     *  pageSize,
     *  routeName,
     *  minPrice,
     *  maxPrice
     * @return
     */
    public PageInfo<RouteVo> favoriteRankByPageAndCondition(RouteVo routeVo);
}
