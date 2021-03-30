package com.itheima.rowMapper;


import com.itheima.pojo.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper {

    /**
     * 这个方法每次遍历时都执行一次。
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        //这里每次执行，都是获取一行数据
        int aid = resultSet.getInt("aid");
        String aname = resultSet.getString("aname");
        float amoney = resultSet.getFloat("amoney");

        Account account = new Account();
        account.setId(aid);
        account.setAccountName(aname);
        account.setMoney(amoney);
        return account;
    }
}
