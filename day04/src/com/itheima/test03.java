package com.itheima;

import java.util.Random;

//使用baiJava产生一个指定数x以内的随机数。

public class test03 {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            int x = 100;// 上界。
            Random random = new java.util.Random();
// 返回0 to x的一个随机数但不会取到x，即返回[0,x)闭开区间的值。
            int rn = random.nextInt(x);
            System.out.println(rn);
        }
    }
}

