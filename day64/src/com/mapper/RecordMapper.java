package com.mapper;

import com.pojo.Goods;
import com.pojo.Order;
import com.pojo.Record;

import java.util.List;

public interface RecordMapper {

    /**
     * 根据id查询信息
     */
    Record queryById(int id);

    /**
     * 根据id查询价格
     */

    Integer queryPriceById(int id);

    /**
     * 查询所有
     */

    List<Record> findAll();

    /**
     * 添加
     */
    Integer addRecord(Record record);

    /**
     * 更新
     */
    Integer updateUser(Record record);

    /**
     * 根据id删除
     */
    void deleteById(int id);

    /**
     *统计每年订单总金额；
     */

    List<Order> yearMoney();

    /**
     *统计2020年每个商品分类下，订单的数量和总金额；
     */
    List<Goods> selectByGoods();

}
