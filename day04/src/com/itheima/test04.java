package com.itheima;

import java.util.Random;
//使用Java产生一个指定区间x到y的随机数。
//取33-66的随机数

public class test04 {
    public static void main(String[] args) {

        for (int i = 33; i <= 66; i++) {

            int x = 33;//下界
            int y = 66;//上界

            Random random = new Random();
            int num = random.nextInt(y - x + 1) + x;

            System.out.println(num);

        }
    }

}
