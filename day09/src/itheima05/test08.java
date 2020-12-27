package itheima05;

import java.util.Arrays;

public class test08 {
    public static void main(String[] args) {
        byte b = 'a';
        System.out.println(b);
        String s = "123456789";
        for (int i = 0; i < s.length(); i++) {
            System.out.print(s.charAt(i));
        }

        char c = s.charAt(1);
        byte b1 = (byte) c;
        System.out.println(b1);
        char[] chars = s.toCharArray();
        System.out.println(Arrays.toString(chars));
        char c1 = chars[1];
        byte b2 = (byte) c1;
        System.out.println(b2);

    }
}
