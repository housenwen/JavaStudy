package test;

import java.util.Scanner;

//提示用户键盘录入一个包含数字和字母的字符串（不做是否包含数字和字母的判断），
// 遍历字符串分别筛选出数字和字母（如有符号不包含在字母里），
// 按照数字在前字母在后的规则组成一个新的字符串，把组成的新字符串打印在控制台
public class test20 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("录入一个包含数字和字母的字符串:");
        String str = scanner.next();

        String s1 = "";
        String s2 = "";

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            if (c>='0'&&c<='9'){
                s1+=c;
            }else if (c>='A'&&c<='Z'||c>='a'&&c<='z'){
                s2+=c;

            }
            }
        String  s3 = s1+s2;

        System.out.println("新字符串为："+s3);
    }
}
