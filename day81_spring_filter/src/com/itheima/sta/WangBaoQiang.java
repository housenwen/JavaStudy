package com.itheima.sta;

import com.itheima.dynamic.IActor;

public class WangBaoQiang implements IActor {
    @Override
    public void act() {
        System.out.println("王宝强表演");
    }

    @Override
    public void sing() {
        System.out.println("王宝强唱歌");
    }
}
