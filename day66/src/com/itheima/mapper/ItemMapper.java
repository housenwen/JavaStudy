package com.itheima.mapper;

import com.itheima.pojo.Item;
import org.apache.ibatis.annotations.Select;

public interface ItemMapper {

    @Select("select * from tb_item where id=#{id}")
    Item findById(Integer id);
}
