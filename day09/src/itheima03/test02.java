package itheima03;

import java.util.Scanner;

//需求：键盘接受一个字符串，程序判断出该字符串是否是对称字符串，并在控制台打印是或不是
//对称字符串：123321、111
//非对称字符串：123123
public class test02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String s1 = scanner.nextLine();

        StringBuilder s2 = new StringBuilder(s1);
//        返回相反的字符序列
        s2.reverse();
        String s3 = s2.toString();

        if (s1.equals(s3)){
            System.out.println("对称字符串");
        }else {
            System.out.println("非对称字符串");
        }
    }
}
