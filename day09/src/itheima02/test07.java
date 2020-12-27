package itheima02;

import java.util.Scanner;

//需求：以字符串的形式从键盘接受一个手机号，将中间四位号码屏蔽
//最终效果为：156****1234
public class test07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入11位手机号：");
        String s = sc.nextLine();

        char b = '*';

        for (int i = 0; i < s.length(); i++) {

            char a = s.charAt(i);

            if (i >= 3 && i <= 6) {
                a = b;

            }
            System.out.print(a);
        }
        System.out.println();

    }



    public static String Str(String s) {
        char b = '*';

        for (int i = 0; i < s.length(); i++) {

            char a = s.charAt(i);

            if (i >= 3 && i <= 6) {
                a = b;
            }
        }

        return s;
    }
}
