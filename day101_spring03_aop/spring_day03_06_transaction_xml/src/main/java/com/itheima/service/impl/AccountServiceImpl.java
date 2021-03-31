package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;

public class AccountServiceImpl implements AccountService {


    private AccountDao accountDao;

    @Override
    public void transfer(String outUser, String inUser, double money) {

        //付款人付钱
        int num = accountDao.updateAccountMoney(outUser, 0 - money);
        if(num!=1){
            System.out.println("转账失败，账户余额不足");
            return;
        }

        //int i=1/0;
        //收款人收钱
        accountDao.updateAccountMoney(inUser,money);
    }


    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }
}
