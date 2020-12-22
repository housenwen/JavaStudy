package com.itheima;
import java.util.Scanner;
public class test06 {
    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个五位数：");

        int num = sc.nextInt();

        if(num>=10000&&num<=99999){

            int g = num%10;
            int s = num/10%10;
            int q = num/1000%10;
            int w = num/10000%10;

            if(g==w&&s==q){

                System.out.println("该数是回文数");


            }else {

                System.out.println("该数不是回文数");

            }


        }else{

            System.out.println("您的输入有误");
        }

    }
}
