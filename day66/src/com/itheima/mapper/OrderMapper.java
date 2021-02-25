package com.itheima.mapper;

import com.itheima.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

public interface OrderMapper {

    @Select("select * from tb_order where order_number=#{number}")
    @Results(value = {
        @Result(column = "id",property = "id",id=true),
        @Result(column = "order_number",property = "orderNumber"),
        @Result(column ="user_id",
                one = @One(select = "com.itheima.mapper.UserMapper.findById",
                        fetchType = FetchType.LAZY),
                property = "tbUser")
    })
    Order findByOrderNumber(@Param("number") String orderNumber);

    @Select("select * from tb_order where order_number=#{number}")
    @Results(value = {
        @Result(column = "id",property = "id",id=true),
        @Result(column = "order_number",property = "orderNumber"),
        @Result(column ="id",
                many = @Many(select = "com.itheima.mapper.OrderDetailMapper.findByOrderId"),
                property = "orderdetailList")
    })
    Order findByOrderNumber2(@Param("number") String orderNumber);
}
