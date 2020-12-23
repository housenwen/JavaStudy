package itheima;

import java.util.Scanner;
//1.创建键盘录入对象
// 2.通过键盘录入本金并赋值给变量bj
// 3.计算存不同年限的本息
// 4.打印本息
//存期 年利率 (%)
// 一年 2.25
// 两年 2.7
// 三年 3.24
// 五年 3.6

public class test14 {
    public static void main(String[] args) {
     Scanner scanner =  new Scanner(System.in);
        System.out.println("请输入本金：");
        int  bj = scanner.nextInt();
        System.out.println("本金为："+bj);

        double a = bj + bj*2.25/100;
        double b = bj + bj*2.7/100*2;
        double c = bj + bj*3.24/100*3;
        double d = bj + bj*3.6/100*5;
        System.out.println("存取一年后的本息是："+a);
        System.out.println("存取两年后的本息是："+b);
        System.out.println("存取三年后的本息是："+c);
        System.out.println("存取五年后的本息是："+d);

    }
}
