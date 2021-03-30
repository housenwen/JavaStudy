package com.itheima.test;

import com.itheima.rowMapper.AccountRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class RowMapperTest {
    public static void main(String[] args) {
        /**
         * 1.查询数据库，针对列名起别名
         * 2.使用beanPropertyRowMapper()来自动映射查看映射的结果。
         * 3.自定义结果集映射，替代beanPropertyRowMapper即可
         */

        //创建数据库连接池，spring自带的
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //配置数据库连接参数
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbvue");
        dataSource.setPassword("root");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        List list = jdbcTemplate.query("select id aid,account_name aname,money amoney from account", new AccountRowMapper());

        System.out.println(list);
    }
}
