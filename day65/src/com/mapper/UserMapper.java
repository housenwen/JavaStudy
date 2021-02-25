package com.mapper;

import com.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    /**
     * 查询所有用户
     * @return
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 给指定用户批量添加角色信息
     * @param userId
     * @param roleIds
     */
    void addRole4User(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    /**
     * 根据角色名称查询具有该角色的用户集合
     * @param roleName
     * @return
     */
    List<User> findUsersByRole(@Param("roleName") String roleName);

    /**
     * 根据用户名查询用户信息,包含用户角色集合和权限集合
     * @param userName
     * @return
     */
    User findUserInfo(@Param("userName") String userName);


}
