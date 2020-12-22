package com.itheima;
import java.util.Scanner;

public class test13 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入星期数");
        int week = sc.nextInt();

        switch (week){

            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("今天要上班");
                break;
            case 6:
            case 7:
                System.out.println("今天休息");
                break;
            default:
                System.out.println("您的输入有误");
                break;


        }


    }
}
