package itheima;

import java.util.Random;

public class test09 {
    public static void main(String[] args) {
        Random random = new Random();
        int [] arr = new int[32];
        for (int i = 0; i <32; i++) {
            arr[i] = (i+1);
            System.out.println(arr[i]);
        }
    }
}
