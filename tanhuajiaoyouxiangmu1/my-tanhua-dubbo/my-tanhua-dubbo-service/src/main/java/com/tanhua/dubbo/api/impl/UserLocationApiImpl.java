package com.tanhua.dubbo.api.impl;

import com.tanhua.dubbo.api.UserLocationApi;
import com.tanhua.dubbo.pojo.UserLocation;
import com.tanhua.dubbo.vo.PageInfo;
import com.tanhua.dubbo.vo.UserLocationVo;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.stream.Collectors;

@DubboService(version = "1.0.0")
public class UserLocationApiImpl implements UserLocationApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 更新用户地理位置
     *
     * @param userId    用户id
     * @param longitude 经度
     * @param latitude  纬度
     * @param address   地址名称
     * @return
     */
    @Override
    public Boolean updateUserLocation(Long userId, Double longitude, Double latitude, String address) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        UserLocation userLocation = this.mongoTemplate.findOne(query, UserLocation.class);
        if (null == userLocation) {
            //没有该用户的位置数据，这里需要插入数据
            userLocation = new UserLocation();
            userLocation.setUserId(userId);
            userLocation.setLocation(new GeoJsonPoint(longitude, latitude));
            userLocation.setId(ObjectId.get());
            userLocation.setAddress(address);
            userLocation.setCreated(System.currentTimeMillis());
            userLocation.setUpdated(userLocation.getCreated());
            userLocation.setLastUpdated(userLocation.getCreated());
            this.mongoTemplate.save(userLocation);
        } else {
            Update update = Update.update("location", new GeoJsonPoint(longitude, latitude))
                    .set("address", address)
                    .set("updated", System.currentTimeMillis())
                    //上一次的更新时间
                    .set("lastUpdated", userLocation.getUpdated());
            this.mongoTemplate.updateFirst(query, update, UserLocation.class);
        }

        return true;
    }

    /**
     * 查询用户地理位置
     *
     * @param userId
     * @return
     */
    @Override
    public UserLocationVo queryByUserId(Long userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return UserLocationVo.format(this.mongoTemplate.findOne(query, UserLocation.class));
    }

    /**
     * 根据位置搜索
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param distance  距离(米)
     * @param page      页数
     * @param pageSize  页面大小
     */
    @Override
    public PageInfo<UserLocationVo> queryUserFromLocation(Double longitude, Double latitude,
                                                          Double distance, Integer page,
                                                          Integer pageSize) {
        //思路：先确定自己的位置，根据范围查找
        PageInfo<UserLocationVo> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageSize);

        //设置分页
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);

        //查询条件
        NearQuery nearQuery = NearQuery.near(new GeoJsonPoint(longitude, latitude), Metrics.KILOMETERS)
                .maxDistance(distance / 1000)
                .with(pageRequest);

        GeoResults<UserLocation> geoResults = this.mongoTemplate.geoNear(nearQuery, UserLocation.class);
        pageInfo.setRecords(geoResults.getContent().stream()
                .map(result -> UserLocationVo.format(result.getContent()))
                .collect(Collectors.toList()));
        return pageInfo;
    }

    // @Override
    // public PageInfo<UserLocationVo> queryUserFromLocation(Double longitude, Double latitude,
    //                                                       Double distance, Integer page,
    //                                                       Integer pageSize) {
    //     //思路：先确定自己的位置，根据范围查找
    //     PageInfo<UserLocationVo> pageInfo = new PageInfo<>();
    //     pageInfo.setPageNum(page);
    //     pageInfo.setPageSize(pageSize);
    //
    //     //设置分页
    //     PageRequest pageRequest = PageRequest.of(page - 1, pageSize,
    //             Sort.by(Sort.Order.desc("location")));
    //
    //     //圆圈的半径，单位要转换成km
    //     Distance distanceObj = new Distance(distance / 1000, Metrics.KILOMETERS);
    //
    //     //画一个圆圈
    //     Circle circle = new Circle(new GeoJsonPoint(longitude, latitude), distanceObj);
    //
    //     //查询条件
    //     Query query = Query.query(Criteria.where("location").withinSphere(circle)).with(pageRequest);
    //
    //     List<UserLocation> userLocationList = this.mongoTemplate.find(query, UserLocation.class);
    //     pageInfo.setRecords(UserLocationVo.formatToList(userLocationList));
    //
    //     // TODO 按照距离排序 实现思路：根据坐标进行计算 目标点与 我的点的距离  按照距离进行排序
    //     return pageInfo;
    // }
}
