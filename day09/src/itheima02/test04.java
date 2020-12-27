package itheima02;


import java.util.Arrays;
import java.util.Scanner;


public class test04 {
    public static void main(String[] args) {
//        int [] arr = {1,2,3,4,56,7,8,9};
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr));
//        System.out.println(Arrays.toString(arr));

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String s = scanner.nextLine();

//char b =  s.charAt(0);
//        System.out.println(b);

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            System.out.println(a);

        }
    }
}
