package itheima03;

import java.util.Arrays;

public class test03 {
    public static void main(String[] args) {

      StringBuilder stringBuilder =new  StringBuilder();

      String s = "123456";

      StringBuilder stringBuilder1 = new StringBuilder(s);

//public StringBuilder append (任意类型) 添加数据，并返回对象本身

      stringBuilder1.append("abc");

      String s2 = stringBuilder1.toString();

      System.out.println(s2);
//public StringBuilder reverse() 返回相反的字符序列
      stringBuilder1.reverse();

      String s3 = stringBuilder1.toString();

        System.out.println(s3);
//public int length() 返回长度 ( 字符出现的个数)
        int a = stringBuilder1.length();

        System.out.println(a);

    }
}
