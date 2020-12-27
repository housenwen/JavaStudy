package itheima02;

import java.util.Scanner;

public class test09 {
    public static void main(String[] args) {
/*
字符串截取：
⚫ String substring(int beginIndex, int endIndex) ：从beginIndex索引位置开始截取，截取到endIndex索引位置，
得到新字符串并返回（包含头，不包含尾）

⚫ String substring(int beginIndex) : 从传入的索引位置处，向后截取，一直截取到末尾，得到新的字符串并返回

  */
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入");
        String s = scanner.nextLine();

        String s1 = s.substring(0,3);
        String s2 = s.substring(7);
        String s3 = "****";

        String s4 = s1 +s3+s2;
        System.out.print(s4);
    }
}
