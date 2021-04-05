package com.itheima.dao;

import com.itheima.pojo.Customer;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CustomerDao {

    @Select("select * from customer")
    public List<Customer> findAll();
}
