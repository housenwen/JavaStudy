package com.mapper;

import com.pojo.Route;

import java.util.List;

public interface RouteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Route record);

    int insertSelective(Route record);

    Route selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Route record);

    int updateByPrimaryKey(Route record);

    List<Route> selectRouteByUserId();
}