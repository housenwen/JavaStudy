package com.itheima.travel.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.travel.dao.FavoriteMapper;
import com.itheima.travel.dao.RouteMapper;
import com.itheima.travel.daoExt.RouteMapperExt;
import com.itheima.travel.pojo.Favorite;
import com.itheima.travel.pojo.FavoriteExample;
import com.itheima.travel.pojo.Route;
import com.itheima.travel.req.*;
import com.itheima.travel.service.AffixService;
import com.itheima.travel.service.FavoriteService;
import com.itheima.travel.utils.BeanConv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private HttpSession session;

    @Autowired
    private FavoriteMapper favoriteMapper;


    //自定义扩展mapper
    @Autowired
    private RouteMapperExt routeMapperExt;

    @Autowired
    private RouteMapper routeMapper;

    @Autowired
    private AffixService affixService;

    @Override
    public PageInfo<RouteVo> findMyFavorite(FavoriteVo favoriteVo) {
        //0.从session中获取当前登录的用户信息
        UserVo userVo =(UserVo) session.getAttribute("user");
       /* //1.当前登录的用户收藏的数据的总数量
        FavoriteExample favoriteExample = new FavoriteExample();
        //组装查询条件：根据user_id查询中间表
        favoriteExample.createCriteria()
                .andUserIdEqualTo(userVo.getId());
        long totalCount = favoriteMapper.countByExample(favoriteExample);

        //2.当前登录的用户收藏的线路信息（分页数据）
        int start = (favoriteVo.getPageNum()-1)*favoriteVo.getPageSize();
        List<Route> routeList = routeMapperExt.findFavoriteRouteByPage(userVo.getId(), start, favoriteVo.getPageSize());

        PageBean pageBean = new PageBean();
        pageBean.setRouteList(routeList);
        pageBean.setTotalCount(totalCount);*/


       //通知mybatis分页插件做分页插件
        PageHelper.startPage(favoriteVo.getPageNum(),favoriteVo.getPageSize());

        List<Route> routeList = routeMapperExt.findFavoriteRouteByPage(userVo.getId());
        //这种方式是错的
//        List<RouteVo> routeVoList = BeanConv.toBeanList(routeList, RouteVo.class);
//        PageInfo<RouteVo> voPageInfo = new PageInfo<>(routeVoList);


        //此时，pageInfo中已经包含了分页的总数量和数据了
        PageInfo<Route> pageInfo = new PageInfo<>(routeList);



        //属性拷贝
        PageInfo<RouteVo> voPageInfo = new PageInfo<>();
        //然后再做pageInfo属性拷贝,再做list集合属性拷贝
        BeanConv.toBean(pageInfo,voPageInfo);
        List<RouteVo> routeVoList = BeanConv.toBeanList(pageInfo.getList(), RouteVo.class);
        voPageInfo.setList(routeVoList);

        //查询旅游线路信息的图片
        //旅游线路的图片信息走缓存
        for(RouteVo routeVo:routeVoList){
            //旅游线路的id封装成AffixVo类型的businessId中
            List<AffixVo> affixVos = affixService.findAffixByBusinessId(AffixVo.builder().businessId(routeVo.getId()).build());
            //赋值给旅游线路
            routeVo.setAffixVoList(affixVos);
        }


        return voPageInfo;
    }
    @Transactional //添加事务
    @Override
    public int addFavorite(FavoriteVo favoriteVo) {

        //0。从session中获取用户信息
        UserVo uservo = (UserVo) session.getAttribute("user");

        //1.向tab_favorite中间表添加数据
        Favorite favorite = new Favorite();
        favorite.setRouteId(favoriteVo.getRouteId());
        favorite.setUserId(uservo.getId());
        favoriteMapper.insertSelective(favorite);

        //测试事务
        int i = 1/0;

        //2.修改route表的count字段+1
        routeMapperExt.updateRouteCountByPrimaryKey(favoriteVo.getRouteId());
        //3.重新查询route表的count字段
        Route route = routeMapper.selectByPrimaryKey(favoriteVo.getRouteId());

        return route.getAttentionCount();
    }


    @Override
    public boolean isFavorited(FavoriteVo favoriteVo) {


        /**
         * 此处的登录判断是多个地方都需要的，所以我们可以做登录拦截器,
         * 此处能进来，都表示拦截器放行。直接获取登录的用户信息。
         */
        UserVo user= (UserVo) session.getAttribute("user");
//        if(user==null){
//            //未登录，也代表了未收藏，显示红色按钮
//            return  false;
//        }

        //根据用户的id和旅游线路的id去tab_favorite表查询数据
        FavoriteExample favoriteExample = new FavoriteExample();
        favoriteExample.createCriteria()
                .andUserIdEqualTo(user.getId())//当前登录的用户的id
                .andRouteIdEqualTo(favoriteVo.getRouteId()); //旅游线路的id，前端传递过来
        //查询
        List<Favorite> favoriteList = favoriteMapper.selectByExample(favoriteExample);
        return favoriteList.size()==1;
    }
}
