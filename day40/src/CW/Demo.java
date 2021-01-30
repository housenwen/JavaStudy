package CW;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("请输入您的QQ号码：");
            String QQ = sc.next();
            System.out.println(checkQQ(QQ));
        }

    }

    private static boolean checkQQ(String qq) {
        String regex = "[1-9]\\d{4,14}";//正则表达式
        return qq.matches(regex);
    }
}
