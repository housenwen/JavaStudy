package itheima02;

import java.util.Scanner;

public class test02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "张三";
        String password = "123456";
        for (int i = 0; i < 3; i++) {
            String string = scanner.next();
           boolean boo = equals(string,name,password,string);
            System.out.println(boo);
        }
    }

    private static boolean equals(String a ,String b,String c,String d) {
        if (a==b&&c==d){
            return true;
        }else {
            return false;
        }
    }
}
