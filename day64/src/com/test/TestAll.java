package com.test;

import com.mapper.RecordMapper;
import com.pojo.Goods;
import com.pojo.Order;
import com.pojo.Record;
import com.utils.SessionUtil;
import org.junit.Test;

import java.sql.Date;
import java.util.List;

public class TestAll {
    /**
     * 根据id查询信息
     */
    @Test
    public void test1(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        Record record = mapper.queryById(321512529);
        System.out.println(record);
        SessionUtil.close();
    }
    /**
     * 根据id查询价格
     */
    @Test
    public void test2(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        Integer price = mapper.queryPriceById(321512357);
        System.out.println(price);
        SessionUtil.close();
    }

    /**
     * 查询所有
     */
    @Test
    public void test3(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        List<Record> recordList = mapper.findAll();
        for (Record record : recordList){
            System.out.println(record);
        }
        SessionUtil.close();
    }

    /**
     * 添加
     */
    @Test
    public void test4(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        Record record = new Record();
        record.setId(5201315);
        record.setDate(Date.valueOf("2020-02-20"));
        record.setPrice(888);
        record.setType("零食");
        Integer count = mapper.addRecord(record);
        SessionUtil.commit();
        System.out.println("本次修改的行数"+count);
        SessionUtil.close();

    }

    /**
     * 更新
     */
    @Test
    public void test5(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        Record record = new Record();
        record.setId(321512539);
        record.setType("生鲜");
        record.setPrice(9999);
        record.setDate(Date.valueOf("2000-2-31"));
        Integer count = mapper.updateUser(record);
        System.out.println(count);
        SessionUtil.commit();
        SessionUtil.close();
    }

    /**
     * 根据id删除
     */
    @Test
    public void test6(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        mapper.deleteById(5201315);
        SessionUtil.commit();
        SessionUtil.close();
    }

    /**
     *统计每年订单总金额；
     */
    @Test
    public void Test7(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        List<Order> orders = mapper.yearMoney();
        for (Order order:orders){
            System.out.println(order);
        }
        SessionUtil.close();
    }

    /**
     *统计2020年每个商品分类下，订单的数量和总金额；
     */
    @Test
    public void Test8(){
        RecordMapper mapper = SessionUtil.getMapper(RecordMapper.class);
        List<Goods> goodsList = mapper.selectByGoods();
        for (Goods goods:goodsList){
            System.out.println(goods);
        }
        SessionUtil.close();
    }

}
