package itheima02;

import java.util.Scanner;

public class test10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入：");
        String s = sc.nextLine();
//        System.out.println(s.toString());

       String change = s.replace("TMD","***");

        System.out.println(change);


    }
}
