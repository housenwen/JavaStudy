package com.itheima.dao;

import com.itheima.pojo.Customer;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerDaoTest {

    @Test
    public void findAll() {

        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建sqlSessionFactory工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        //创建sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //动态代理创建dao对象
        CustomerDao customerDao = sqlSession.getMapper(CustomerDao.class);

        List<Customer> customerList = customerDao.findAll();
        for(Customer c:customerList){
            System.out.println(c);
        }
    }
}