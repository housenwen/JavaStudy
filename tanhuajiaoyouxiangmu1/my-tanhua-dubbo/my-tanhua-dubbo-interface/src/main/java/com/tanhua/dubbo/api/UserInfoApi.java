package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoApi {

    /**
     * 完善个人信息
     *
     * @param userInfo
     * @return
     */
    Boolean save(UserInfo userInfo);

    /**
     * 更新个人信息，必须包含userId字段
     *
     * @param userInfo
     * @return
     */
    Boolean update(UserInfo userInfo);

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    UserInfo queryByUserId(Long userId);

    /**
     * 根据用户id列表查询用户信息列表
     *
     * @param userIdList
     * @return
     */
    List<UserInfo> queryByUserIdList(List<Object> userIdList);

    /**
     * 根据用户id列表查询用户信息列表
     *
     * @param userIdList
     * @return
     */
    Map<Long, UserInfo> queryMapByUserIdList(List<Object> userIdList);

    /**
     * 根据用户id列表查询用户信息列表
     *
     * @param userIdList
     * @return
     */
    List<UserInfo> queryLikeUserName(List<Object> userIdList, String userName);


}
