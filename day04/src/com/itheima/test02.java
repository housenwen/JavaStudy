package com.itheima;

//java 使用Math.random()产生一个100以内的随机数;

public class test02 {
    public static void main(String[] args) {

        int num = (int) (Math.random() * 100);
//        Math.random();返回的是一个大于等于0小于1的double类型。
        System.out.println(num);

    }
}

