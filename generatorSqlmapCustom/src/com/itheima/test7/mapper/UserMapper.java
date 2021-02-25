package com.itheima.test7.mapper;

import com.itheima.test7.pojo.User;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据角色名称查询对应的用户信息
     * @param roleName
     * @return
     */
    List<User> findUsersByRoleName(String roleName);

    /**
     * 根据用户名称查询用户的信息，包含角色和权限信息
     * @param userName
     * @return
     */
    User findUserInfoByUserName(String userName);
}