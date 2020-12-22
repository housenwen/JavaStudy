package com.itheima;
import java.util.Scanner;

public class test04 {

    public static void main(String[] args) {

        Scanner year = new Scanner(System.in);
        System.out.println("请输入年份：");
        int num = year.nextInt();
        if(num%4==0&&num%100!=100){

            System.out.println("该年是闰年");

        }else if (num%400==0){

            System.out.println("该年是闰年");

        }else {

            System.out.println("该年不是闰年");
        }


    }
}
