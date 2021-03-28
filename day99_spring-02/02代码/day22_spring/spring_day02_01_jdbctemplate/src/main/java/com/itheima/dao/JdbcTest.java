package com.itheima.dao;

import com.itheima.pojo.Account;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {

    public static void main(String[] args) throws Exception {


        Class.forName("com.mysql.jdbc.Driver");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/itheima124_day22_spring", "root", "root");

        String sql = "select id,account_name name,money qian from account ";
        PreparedStatement ps = connection.prepareStatement(sql);

       // ps.setString(1,"1");


        ResultSet resultSet = ps.executeQuery();

        List<Account> list = new ArrayList<>();
        //遍历结果集
        while (resultSet.next()){
            //一行数据的映射
            Account account = mapRow(resultSet);
            //添加到集合中
            list.add(account);
        }

        System.out.println(list);

        resultSet.close();
        ps.close();
        connection.close();
    }


    public static Account mapRow(ResultSet resultSet) throws Exception{
        //处理一行数据,封装成一个account对象
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        float qian = resultSet.getFloat("qian");

        //手动封装数据
        Account account = new Account();
        account.setId(id);
        account.setAccountName(name);
        account.setMoney(qian);
        return account;
    }
}
