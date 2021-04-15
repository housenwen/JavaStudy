package com.itheima.travel.daoExt;

import com.itheima.travel.pojo.Route;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

public class RouteMapperExtSqlProvider {


    public String favoriteRankByPageAndCondition(@Param("routeName") String routeName,
                                                @Param("minPrice") double minPrice,
                                                @Param("maxPrice") double maxPrice){
        StringBuffer sb = new StringBuffer("SELECT * FROM tab_route WHERE 1=1 ");

        //sql拼接
        /*
            1.where关键字
            2.sql拼接之间的空格
         */
        if(StringUtils.isNotEmpty(routeName)){
            sb.append("and route_name LIKE concat('%',#{routeName},'%') ");
        }

        if(minPrice>0){
            sb.append("and price>#{minPrice} ");
        }

        if(maxPrice>0){
            sb.append("and price<#{maxPrice} ");
        }

        sb.append("ORDER BY attention_count DESC");

        return sb.toString();
    }
}
