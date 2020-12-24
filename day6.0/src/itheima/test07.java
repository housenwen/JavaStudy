package itheima;

import java.util.Scanner;
///从键盘上录入一个大于 100 的三位数,
//        求出 100 到该数字之间满足如下要求的数字之和:
//        1.数字的个位数不为 7;
//2.数字的十位数不为 5;
//3.数字的百位数不为 3;

public class test07 {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个大于100的三位数:");
        int num = scanner.nextInt();
        int g =num%10;
        int s =num/10%10;
        int b =num/100%10;
        int sum = 0;

        for (int i = 100; i <= num; i++) {
            if (g!=7&&s!=5&&b!=3){
            sum +=i;
                System.out.println(i);
        }
        }
        System.out.println("和为："+sum);

    }
}
