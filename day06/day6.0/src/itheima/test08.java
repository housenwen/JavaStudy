package itheima;

import java.util.Scanner;

public class test08 {
    public static void main(String[] args) {
        //定义一个变量，记录满足条件数字的累计和
        int sum = 0;
        //键盘输入一个数字 100-999
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        //使用循环从100-到键盘输入的数字，获取其中的每一个数字
        for (int i = 100; i <= n; i++) {
            //获取当前数字的个位、十位、百位的数字
            int ge = i % 10;
            int shi = i / 10 % 10;
            int bai = i / 100 % 10;
            //判断当前的数字是否（个位数不为7 并且十位数不为5 并且 百位数不为3）
            if (ge!= 7 && shi!=5 && bai!=3) {
                //把满足条件的数字进行累计和的操作
                sum += i;
                System.out.println(i);
            }
        }
        System.out.println("满足如下要求的数字之和:" + sum );
    }
}
