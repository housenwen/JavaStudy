package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public int updateAccountMoney(String accountName, double money) {
        String sql = "UPDATE account SET money = money + ? WHERE account_name=?  AND money+?>0";
        int update = jdbcTemplate.update(sql, money, accountName,money);
        return update;
    }



}
