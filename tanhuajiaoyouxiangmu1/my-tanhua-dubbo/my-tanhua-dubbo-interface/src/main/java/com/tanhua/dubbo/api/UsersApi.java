package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.Users;
import com.tanhua.dubbo.vo.PageInfo;

import java.util.List;

public interface UsersApi {

    /**
     * 保存好友关系
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @return
     */
    String saveUsers(Long userId, Long friendId);


    /**
     * 删除好友数据
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @return
     */
    Boolean removeUsers(Long userId, Long friendId);

    /**
     * 根据用户id查询全部Users列表
     *
     * @param userId
     * @return
     */
    List<Users> queryAllUsersList(Long userId);

    /**
     * 根据用户id查询Users列表(分页查询)
     *
     * @param userId
     * @return
     */
    PageInfo<Users> queryUsersList(Long userId, Integer page, Integer pageSize);
}
