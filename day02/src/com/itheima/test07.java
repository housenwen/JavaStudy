package com.itheima;
import java.util.Scanner;
public class test07 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入您的年龄：");
        int num = sc.nextInt();



            if (num >= 0 && num <= 130) {

                if (num >= 18) {

                    System.out.println("您已成年");

                } else {


                    System.out.println("您还没成年");

                }


            }
            else {

                System.out.println("您的输入有误，请重新输入");


        }



    }
}
