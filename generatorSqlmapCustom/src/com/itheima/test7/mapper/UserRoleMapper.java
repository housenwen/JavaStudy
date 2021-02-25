package com.itheima.test7.mapper;

import com.itheima.test7.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserRoleMapper {
    int insert(UserRole record);

    int insertSelective(UserRole record);

    int batchInsert(@Param("userId") Integer userId,@Param("roleIds") List<Integer> roleIds);
}