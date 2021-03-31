package com.itheima.dao;

public interface AccountDao {

    /**
     *
     * @param accountName
     * @param money
     * @return  返回值0，表示修改失败。等于1才正确
     */
    public int updateAccountMoney(String accountName,double money);
}
