package Itheima03;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class test03 {
    public static void main(String[] args) {

        String s= "1234567234567823456789";
       int length =  s.length();
        double i = parseDouble(s);

        System.out.println(i);

        System.out.println(length);

      String s1 ="abc";

        String s3 = s1.toUpperCase();
        System.out.println(s3);

        String s2 = s1.toLowerCase();

        System.out.println(s2);

    }
}
