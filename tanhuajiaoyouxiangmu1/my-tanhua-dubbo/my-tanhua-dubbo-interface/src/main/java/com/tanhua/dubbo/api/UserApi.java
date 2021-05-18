package com.tanhua.dubbo.api;

import com.tanhua.dubbo.pojo.User;

public interface UserApi {

    /**
     * 根据手机号查询用户
     *
     * @param mobile
     * @return
     */
    User queryByMobile(String mobile);

    /**
     * 根据手id查询用户
     *
     * @param id
     * @return
     */
    User queryById(Long id);


    /**
     * 注册新用户,返回用户id
     *
     * @param mobile
     * @return
     */
    Long save(String mobile);

    /**
     *更新手机号
     * @param userId
     * @param mobile
     * @return
     */
    Boolean updateNewPhone(Long userId,String mobile);
}
