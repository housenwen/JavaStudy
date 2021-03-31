package com.itheima.proxy.sta;

public class SongZhe implements IActor {

    private IActor wangBaoQiang = new WangBaoQiang();

    @Override
    public void act() {
        System.out.println("表演之前的一些商谈工作*******");
        wangBaoQiang.act();
    }

    @Override
    public void sing() {
        System.out.println("唱歌之前的一些商谈工作！！！！");
        wangBaoQiang.sing();
    }
}
