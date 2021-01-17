package regex;

import java.util.Scanner;

public class Demo1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("请输入你的QQ号码：");
            String qq = sc.next();

            System.out.println(checkQQ2(qq));
        }
    }

    //使用正则表达式验证
    private static boolean checkQQ2(String qq) {
        String regex = "[1-9]\\d{4,14}";//正则表达式
        return qq.matches(regex);
    }
}
