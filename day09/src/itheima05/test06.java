package itheima05;

import java.util.Arrays;
import java.util.Scanner;
//5、编写一个Java程序，提示用户输入一串字符串，要求字符串中必须存在字母（需要代码判断）
//
//	1.若不符合要求，则提示用户重新输入直至符合要求为止
//
//	2.若符合要求，则判断字符串中大写字母出现次数并打印。
public class test06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 1; i++) {
            System.out.println("请输入一串字符串(必须存在字母)：");
            String s = scanner.nextLine();
            char[] chars = s.toCharArray();
            System.out.println(Arrays.toString(chars));
            for (int j = 0; j < chars.length; j++) {
                char ch = chars[j];
                if (ch>='a'&&ch<='z'||ch<='Z'&&ch>='A'){
                    i--;
                }
            }
        }
    }
}
