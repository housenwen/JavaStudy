package com.itheima.travel.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.travel.daoExt.RouteMapperExt;
import com.itheima.travel.pojo.Route;
import com.itheima.travel.pojoExt.RouteExt;
import com.itheima.travel.req.AffixVo;
import com.itheima.travel.req.CategoryVo;
import com.itheima.travel.req.RouteVo;
import com.itheima.travel.req.SellerVo;
import com.itheima.travel.service.AffixService;
import com.itheima.travel.service.RouteService;
import com.itheima.travel.utils.BeanConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteServiceImpl implements RouteService {


    @Autowired
    private RouteMapperExt routeMapperExt;

    @Autowired
    private AffixService affixService;

    @Override
    public RouteVo findRouteDetailsByRid(RouteVo routeVo) {


        //1.查询tab_route,tab_seller,tab_category表数据
        RouteExt routeExt = routeMapperExt.findRouteDetailsByPrimaryKey(routeVo.getId());

        //2.调用affixService查询图片信息即可
        AffixVo affixVo = AffixVo.builder()
                .businessId(routeVo.getId())
                .build();
        List<AffixVo> affixVoList = affixService.findAffixByBusinessId(affixVo);


        //属性拷贝
        RouteVo routeVo2 = new RouteVo();
        routeVo2.setAffixVoList(affixVoList);
        //category
        CategoryVo categoryVo = BeanConv.toBean(routeExt.getCategory(), CategoryVo.class);
        routeVo2.setCategoryVo(categoryVo);
        //seller
        SellerVo sellerVo = BeanConv.toBean(routeExt.getSeller(), SellerVo.class);
        routeVo2.setSellerVo(sellerVo);
        //route
        BeanConv.toBean(routeExt,routeVo2);






        return routeVo2;
    }

    @Override
    public PageInfo<RouteVo> favoriteRankByPageAndCondition(RouteVo routeVo) {


        //通知mybatis分页查询
        PageHelper.startPage(routeVo.getPageNum(),routeVo.getPageSize());

        String routeName = routeVo.getRouteName();
        double minPrice = routeVo.getMinPrice().doubleValue();
        double maxPrice = routeVo.getMaxPrice().doubleValue();
        //调用dao方法
        List<Route> routeList = routeMapperExt.favoriteRankByPageAndCondition(routeName, minPrice, maxPrice);

        //属性拷贝
        PageInfo<Route> pageInfo = new PageInfo<>(routeList);


        PageInfo<RouteVo> voPageInfo = new PageInfo<>();
        //total属性拷贝
        BeanConv.toBean(pageInfo,voPageInfo);
        //集合属性拷贝
        List<RouteVo> routeVoList = BeanConv.toBeanList(pageInfo.getList(), RouteVo.class);
        voPageInfo.setList(routeVoList);



        //图片信息
        routeVoList.forEach(r->{
            //针对每个旅游线路信息，查询对应的图片信息;
            AffixVo affixVo = AffixVo.builder().businessId(r.getId()).build();
            List<AffixVo> affixVos = affixService.findAffixByBusinessId(affixVo);
            //图片赋值给routeVo
            r.setAffixVoList(affixVos);
        });



        return voPageInfo;
    }
}
