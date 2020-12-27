package itheima03;

import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        String s = "1234567895678";
//        String [] s1 = s.split("2");
////        String s3 = s1.toString();
////        System.out.println(s3);
//        String s2 = s1[0];
//        System.out.println(s2);
//        String s4 = s1[1];
//        System.out.println(s4);
        String [] s5 = s.split("5",3);

//        String s7 = s5[1];
//        String s9 = s5[0];
////        String s10 = s5[2];
//        System.out.println(s7);
//        System.out.println(s9);
////        System.out.println(s10);
        System.out.println(Arrays.toString(s5));

    }
}
