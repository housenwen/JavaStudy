package com.itheima.dao;

import com.itheima.pojo.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
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
