package com.itheima.test7.mapper;

import com.itheima.test7.pojo.RoleAccess;

public interface RoleAccessMapper {
    int insert(RoleAccess record);

    int insertSelective(RoleAccess record);
}