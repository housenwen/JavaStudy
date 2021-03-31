package com.itheima.service.impl;

import com.itheima.dao.AccountDao;
import com.itheima.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {


    @Autowired
    private AccountDao accountDao;

    @Override
    @Transactional(readOnly = false)
    public void transfer(String outUser, String inUser, double money) {

        //付款人付钱
        int num = accountDao.updateAccountMoney(outUser, 0 - money);
        if(num!=1){
            System.out.println("转账失败，账户余额不足");
            return;
        }

        int i=1/0;
        //收款人收钱
        accountDao.updateAccountMoney(inUser,money);
    }



}
