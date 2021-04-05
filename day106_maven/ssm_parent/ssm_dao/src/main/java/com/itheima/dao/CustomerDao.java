package com.itheima.dao;

import com.itheima.pojo.Customer;

import java.util.List;

public interface CustomerDao {

    public List<Customer> findAll();
}
