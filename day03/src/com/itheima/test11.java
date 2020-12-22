package com.itheima;

public class test11 {
    public static void main(String[] args) {

        int a = 10;
        int b = 20;
        int c = 30;
        int d = 40;

        int x=(a>b)?a:b;
        int y=(x>c)?x:c;
        int z=(y>d)?y:d;

        System.out.println("最大值为："+z);

        
    }
}
