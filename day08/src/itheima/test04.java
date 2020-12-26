package itheima;


import java.util.Random;
import java.util.Scanner;

//2、系统产生一个70-80之间的随机数，请猜出这个数字是多少。
//
//（猜中之后程序就结束，没有猜中则继续猜）
//
//详细步骤：
//
//	①   、利用Random产生一个随机数，范围70-80（包括70和80）。
//
//	②、提示用户键盘录入猜的数字
//
//	③、比较这两个数字(用if语句)
//
//      	  	大了：给出提示大了，并且继续猜
//
//        	小了：给出提示小了，并且继续猜
//
//        	猜中了：给出提示，恭喜你，猜中了，并且结束循环
public class test04 {
    public static void main(String[] args) {
        Random random = new Random();
        int num = random.nextInt(80 - 70 + 1) + 70;
        System.out.println("随机数为：" + num);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个70-80的数字：");
        for (int i = 0; i < 1; i++) {
            int guess = scanner.nextInt();
            if (guess > num) {
                System.out.println("您输入的数值大了");
                i--;
            } else if (guess < num) {
                System.out.println("您输入的数值小了");
                i--;
            } else  {
                System.out.println("恭喜你，猜中了");
            }
        }
    }
    }

