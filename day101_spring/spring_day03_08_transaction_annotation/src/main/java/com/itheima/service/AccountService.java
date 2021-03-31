package com.itheima.service;

public interface AccountService {


    /**
     *
     * @param outUser  付款人
     * @param inUser  收款人
     * @param money   金额
     */
    public void transfer(String outUser,String inUser,double money);
}
