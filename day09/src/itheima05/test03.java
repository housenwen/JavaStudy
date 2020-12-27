package itheima05;
//3、统计一个字符串中大写字母字符，小写字母字符，数字字符出现的次数。(不考虑其他字符)
//
//  分析：
//
//            A:键盘录入一个字符串数据
//
//            B:定义三个统计变量，初始化值都是0
//
//            C:遍历字符串，得到每一个字符
//
//            D:拿字符进行判断
//
//                    假如ch是一个字符。
//
//                    大写：ch>='A' && ch<='Z'
//
//                   小写：ch>='a' && ch<='z'
//
//                   数字：ch>='0' && ch<='9'
//
//            E:输出结果

import java.util.Scanner;

public class test03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String s = scanner.nextLine();
        System.out.println(s);
        char[] chars = s.toCharArray();
        int c1 = 0;
        int c2 = 0;
        int c3 = 0;

        for (int i = 0; i < chars.length; i++) {
            char ch = chars[i];
            if (ch>='A' && ch<='Z'){
                c1++;
            }else if (ch>='a' && ch<='z'){
                c2++;
            }else if (ch>='0' && ch<='9'){
                c3++;
            }
        }

        System.out.println("大写字母字符出现的次数"+c1);
        System.out.println("小写字母字符出现的次数"+c2);
        System.out.println("数字字符出现的次数"+c3);

    }
}
