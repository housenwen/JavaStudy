package com.itheima.test;

import com.itheima.pojo.Account;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class JdbcTemplateCRUDTest {
    public static void main(String[] args) {
        //创建数据库连接池，spring自带的
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        //配置参数
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbvue");
        dataSource.setPassword("root");
        dataSource.setUsername("root");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //创建JdbcTemplate核心对象
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        //1.新增
//        jdbcTemplate.update("insert into account values (?,?,?)","4","小爽","2000");

        //  2.根据用户id修改用户
//        jdbcTemplate.update("update account set money = ? where id=?",6666,1);

        //  3.根据用户id删除用户,企业开发都是逻辑删除
//        jdbcTemplate.update("delete from account where id = ?",4);

        // 4.查询所有用户
        /**
         *  BeanPropertyRowMapper: 用于处理数据库的数据到实体的映射的，作
         *                          用和mybatis的 autoMapping=true 一模一样
         *                          autoMapping=true：自动映射，表的列名和属性名一致，即可将数据映射到实体对应的属性中
         *
         */

//        List<Account> list = jdbcTemplate.query("select * from account", new BeanPropertyRowMapper<Account>(Account.class));
//        for(Account account:list){
//            System.out.println(account);
//        }

        // 4.根据id查询单个用户
//        Account account = jdbcTemplate.queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<>(Account.class),1);
//        System.out.println(account);

        //5.查询当前用户个数
        Integer count = jdbcTemplate.queryForObject("select count(*) from account", Integer.class);
        System.out.println(count);



    }
}
