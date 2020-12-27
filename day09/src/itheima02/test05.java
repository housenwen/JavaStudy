package itheima02;

import java.util.Scanner;

public class test05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入:");
        String s = scanner.nextLine();
        int c1 =0;
        int c2 =0;
        int c3 = 0;

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            System.out.println(a);
            if (a>='0'&&a<='9'){
                c1++;
            }else if (a>='A'&&a<='Z'){
                c2++;
            }else if(a>='a'&&a<='z'){
                c3++;
            }
        }
        System.out.println("数字字符出现的次数："+c1);
        System.out.println("大写字母字符出现的次数："+c2);
        System.out.println("小写字母字符出现的次数："+c3);
    }
}
