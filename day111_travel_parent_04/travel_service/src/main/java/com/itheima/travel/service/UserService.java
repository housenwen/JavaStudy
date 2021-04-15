package com.itheima.travel.service;

import com.itheima.travel.req.UserVo;

public interface UserService {


    /*
        注册
     */
    public boolean registerUser(UserVo userVo);


    /**
     * 登录
     * @param userVo
     * @return
     */
    public UserVo loginUser(UserVo userVo);


    /**
     * 退出
     * @return
     */
    public boolean logoutUser();


    /**
     * 是否登录
     * @return
     */
    public boolean isLogin();


}
