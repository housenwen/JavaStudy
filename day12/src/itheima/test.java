package itheima;

import java.util.Arrays;

public class test {
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        sb.append("aaaa");


        char [] chars = sb.toString().toCharArray();

        System.out.println(sb.toString());

        System.out.println(Arrays.toString(chars));

        sb=null;

        System.out.println(sb.toString());



    }
}
