package com.tanhua.dubbo.api;

import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.dubbo.vo.UserLocationVo;

public interface UserLocationApi {

    /**
     * 更新用户地理位置
     *
     * @param userId    用户id
     * @param longitude 经度
     * @param latitude  纬度
     * @param address   地址名称
     * @return
     */
    Boolean updateUserLocation(Long userId, Double longitude, Double latitude, String address);

    /**
     * 查询用户地理位置
     *
     * @param userId
     * @return
     */
    UserLocationVo queryByUserId(Long userId);

    /**
     * 根据位置搜索
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param distance  距离(米)
     * @param page      页数
     * @param pageSize  页面大小
     */
    PageInfo<UserLocationVo> queryUserFromLocation(Double longitude, Double latitude, Double distance, Integer page, Integer pageSize);

}
