package com.itheima.travel.daoExt;

import com.itheima.travel.pojo.Route;
import com.itheima.travel.pojoExt.RouteExt;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RouteMapperExt {


    /**
     *
     * @param userId  用户的id
     * @param start  起始位置
     * @param pageSize  每页显示的数量
     * @return
     */
    //@Select("SELECT r.* FROM tab_favorite f,tab_route r WHERE f.route_id = r.id AND f.user_id=#{userId}  LIMIT #{start},#{pageSize}")
    /*public List<Route> findFavoriteRouteByPage(@Param("userId") Long userId,
                                               @Param("start") int start,
                                               @Param("pageSize") int pageSize);*/

    @Select("SELECT * FROM tab_favorite f,tab_route r WHERE f.route_id = r.id AND f.user_id=#{userId} ")
    public List<Route> findFavoriteRouteByPage(@Param("userId") Long userId);


    @Update("update tab_route set attention_count = attention_count+1 where id = #{routeId}")
    public void updateRouteCountByPrimaryKey(@Param("routeId") long routeId);

    /**
     *  @Results: 声明开始自定义结果集映射 ，作用和<resultMap></>标签一样
     *      @Result: 用于配置列和属性的映射，作用和<result></>标签一致
     *
     *     注意：注解方式  autoMapping=true 是默认开启的。
     *
     * @param routeId
     * @return
     */
    @Select("SELECT * FROM tab_route WHERE id = #{routeId}")
    @Results({
            @Result(column = "id",property = "id",id = true),
            @Result(column = "category_id",property = "category",
                    one = @One(select = "com.itheima.travel.dao.CategoryMapper.selectByPrimaryKey")),
            @Result(column = "seller_id",property = "seller",one = @One(select = "com.itheima.travel.dao.SellerMapper.selectByPrimaryKey"))
    })
    public RouteExt findRouteDetailsByPrimaryKey(@Param("routeId") long routeId);



    @SelectProvider(type = RouteMapperExtSqlProvider.class,method = "favoriteRankByPageAndCondition")
    public List<Route> favoriteRankByPageAndCondition(@Param("routeName") String routeName,
                                                @Param("minPrice") double minPrice,
                                                @Param("maxPrice") double maxPrice);
}
