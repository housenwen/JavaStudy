package com.itheima.test;

import com.itheima.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class JdbcTemplateTest {

    public static void main(String[] args) {
        //创建数据库连接池，spring自带的
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //配置数据库连接参数
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbvue");
        dataSource.setPassword("root");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //创建JdbcTemplate核心对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //操作数据库结
        jdbcTemplate.update("delete from account where id = 3");

        //操作数据库结
        List<Account> accountList = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<>(Account.class));
        System.out.println(accountList);
    }
}
