package com.itheima.test7.mapper;

import com.itheima.test7.pojo.Access;

public interface AccessMapper {
    int deleteByPrimaryKey(Integer accessId);

    int insert(Access record);

    int insertSelective(Access record);

    Access selectByPrimaryKey(Integer accessId);

    int updateByPrimaryKeySelective(Access record);

    int updateByPrimaryKey(Access record);
}