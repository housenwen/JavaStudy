package com.itheima.dao.impl;

import com.itheima.dao.AccountDao;
import com.itheima.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class AccountDaoImpl implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Account> findAll() {
        String sql = "select * from account";
        return jdbcTemplate.query(sql,new BeanPropertyRowMapper<Account>(Account.class));
    }



    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
