package com.itheima;
import java.util.Scanner;

public class test12 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入星期数：");
        int week = sc.nextInt();

        switch (week){

            case 1 :
                System.out.println("week1");
                break;
                case 2 :
                System.out.println("week2");
                break;
                case 3 :
                System.out.println("week3");
                break;
                case 4 :
                System.out.println("week4");
                break;
                case 5 :
                System.out.println("week5");
                break;
                case 6 :
                System.out.println("week6");
                break;
                case 7 :
                System.out.println("week7");
                break;
            default:
                System.out.println("您的输入有误");
                break;




        }




    }
}
