package itheima;

import java.util.Scanner;

public class test01 {
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