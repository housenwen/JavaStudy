package com.itheima.dao;

import com.itheima.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class AccountDao {

    public static void main(String[] args) {

        //创建连接池对象
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //设置连接参数
        dataSource.setUrl("jdbc:mysql://localhost:3306/itheima124_day22_spring");
        dataSource.setPassword("root");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //创建jdbcTemplate对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


        //增
//        String sql = "insert into account values(?,?,?)";
//        jdbcTemplate.update(sql,"4","司藤","0");

        //删
//        String sql = "delete from account where id = ?";
//        jdbcTemplate.update(sql,"4");

        //改
//        String sql = "update account set money=? where id = ?";
//        jdbcTemplate.update(sql,"1000","4");


        //查询所有数据的数量
//        String sql = "select count(*) from account";
//        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
//        System.out.println("数据的总数量："+count);

        //查询所有数据
//        String sql = "select * from account";
//        List<Account> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class));
//        query.forEach(account -> {
//            System.out.println(account);
//        });

        //查询单条数据
//        String sql = "select * from account where id = ?";
//        Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<Account>(Account.class), "1");
//        System.out.println(account);


//        //通过jdbcTemplate的api操作数据库即可
//        String sql = "select * from account where id = ?";
//        //BeanPropertyRowMapper:等同于mybatis中的autoMapping=true
//        List<Account> accountList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Account>(Account.class), "1");
//        accountList.forEach(account -> {
//            System.out.println(account);
//        });




        String sql = "select id,account_name name,money qian from account where id = ?";
        Account account = jdbcTemplate.queryForObject(sql, new AccountRowMapper(), "1");
        System.out.println(account);

    }
}
