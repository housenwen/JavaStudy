package com.itheima.service.impl;

import com.itheima.dao.CustomerDao;
import com.itheima.pojo.Customer;
import com.itheima.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired //自动注入
    private CustomerDao customerDao;

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
