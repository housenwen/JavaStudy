package com.itheima.sta2;

import com.itheima.sta.WangBaoQiang;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyInvocationHandler {
    WangBaoQiang wangBaoQiang = new WangBaoQiang();

    //代理类功能的具体实现（增强+原有功能）
    public void invoke(Method method){
        //增强的功能
        System.out.println("增强的工作：商谈工作！！！！");
        try {
            method.invoke(wangBaoQiang,null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
