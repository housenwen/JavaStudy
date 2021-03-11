package com.itheima.sta2;

import java.lang.reflect.Method;

public class SongZhe implements IActor {

    MyInvocationHandler ih = new MyInvocationHandler();

    private static Method m1;
    private static Method m2;

    static {

        try {
            m1=Class.forName("com.itheima.sta2.IActor").getMethod("act",null);
            m2=Class.forName("com.itheima.sta2.IActor").getMethod("sing",null);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void act() {

    }

    @Override
    public void sing() {

    }
}
