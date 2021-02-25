package com.itheima.mapper;

import com.itheima.pojo.Orderdetail;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDetailMapper {


    @Select("select * from tb_orderdetail where order_id=#{id}")
    @Results(
           value = {
                    @Result(column = "id",property = "id",id=true),
                    @Result(column = "total_price",property = "totalPrice"),
                    @Result(column = "status",property = "status"),
                    @Result(column = "item_id",
                            one = @One(select = "com.itheima.mapper.ItemMapper.findById"),
                            property = "tbItem"
                    )
            }
    )
    List<Orderdetail> findByOrderId(Integer id);
}
