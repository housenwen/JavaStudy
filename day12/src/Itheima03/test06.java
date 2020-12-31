package Itheima03;

import java.util.Arrays;
import java.util.Random;

public class test06 {
    public static void main(String[] args) {
        Random r = new Random();
        int [] ints = new int[5];

        for (int i = 0; i < ints.length; i++) {
            ints[i] = r.nextInt(10-2+1)+2;
        }
        System.out.println(Arrays.toString(ints));
    }
}
