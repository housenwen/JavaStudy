package com.itheima.sta;

import com.itheima.dynamic.IActor;

public class SongZhe implements IActor {

    private IActor wangBaoQiang = new WangBaoQiang();

    @Override
    public void act() {
        System.out.println("商谈工作!!!!!");
        wangBaoQiang.act();
    }

    @Override
    public void sing() {
        System.out.println("商谈工作！！！！");
        wangBaoQiang.sing();
    }
}