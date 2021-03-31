package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import org.springframework.jdbc.core.JdbcTemplate;

public class AccountDaoImpl implements AccountDao {


    private JdbcTemplate jdbcTemplate;


    @Override
    public int updateAccountMoney(String accountName, double money) {
        String sql = "UPDATE account SET money = money + ? WHERE account_name=?  AND money+?>0";
        int update = jdbcTemplate.update(sql, money, accountName,money);
        return update;
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
