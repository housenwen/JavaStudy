package itheima;

import java.util.Random;

public class test08 {
    public static void main(String[] args) {
        Random random = new Random();
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10) + 1;
            System.out.println(arr[i]);
        }
        System.out.println("-------------------");
        change(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int[] change(int[] brr) {
        int temp = brr[0];
        brr[0] = brr[brr.length - 1];
        brr[brr.length - 1] = temp;
        return brr;
    }

}

