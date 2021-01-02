package itheima06;

import java.util.Random;

public class test02 {
    public static void main(String[] args) {

        Random random = new Random();

        int[] arr = new int[5];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(99)+1;
            System.out.println(arr[i]);
        }
        int avg = getScore(arr);

        System.out.println("平均数为："+avg);

    }

    private static int getScore(int[] arr) {

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            sum+=arr[i];

        }

        return sum/arr.length;
    }
}
