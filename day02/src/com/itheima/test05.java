package com.itheima;
import java.util.Scanner;

public class test05 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个三位数");
        int num = sc.nextInt();
        if(num>=100&&num<=999){

            int g = num%10;
            int s = num/10%10;
            int b = num/100%10;

            int x = g*g*g;
            int y = s*s*s;
            int z = b*b*b;

            if (num==x+y+z){

                System.out.println("该数是水仙花数");

            }else{

                System.out.println("该数不是水仙花数");

            }


        }else{

            System.out.println("您输入有误");
        }


    }
}
