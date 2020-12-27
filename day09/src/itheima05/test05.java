package itheima05;
//5、编写一个Java程序，提示用户输入一串字符串，要求字符串中必须存在字母（需要代码判断）
//
//	1.若不符合要求，则提示用户重新输入直至符合要求为止
//
//	2.若符合要求，则判断字符串中大写字母出现次数并打印。
import java.util.Scanner;

public class test05 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int c1 = 0;
        int c2 = 0;

        for (int i=0;i<1;i++) {
            System.out.println("请输入一串字符串(必须存在字母)：");

            String s = scanner.nextLine();
            char[] chars = s.toCharArray();


            for (int j = 0; j < chars.length; j++) {
               char c = chars[j];
                if (c>='a'&&c<='z'){
                    c1++;
                }else if(c<='Z'&&c>='A') {
                   c2++;
                    System.out.println(chars[j]);
                }
            }
        }

        System.out.println(c2);

    }
}
