package itheima05;
//5、编写一个Java程序，提示用户输入一串字符串，要求字符串中必须存在字母（需要代码判断）

//        1.若不符合要求，则提示用户重新输入直至符合要求为止
//
//        2.若符合要求，则判断字符串中大写字母出现次数并打印。

import java.util.Scanner;

public class test07 {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        while (true){
            System.out.println("请输入一个带字母的字符串：");
            String str = input.next();
            int c1 = 0;
            int c2 = 0;

            for (int i = 0; i < str.length(); i++) {
                byte b = (byte) str.charAt(i);
                if (b>64&&b<91){
                    c1++;
                    System.out.println(str.charAt(i));
                }else if (b>96&&b<123){
                    c2++;
                }
            }
            if (c1==0&&c2==0){
                System.out.println("输入错误，请重新输入");
            }else if (c1==0){
                System.out.println("输入的字符串中含大写字母0个");
                break;
            }
            else {
                System.out.println("大写字母有"+c1+"个");
                break;
            }
        }
    }
}
