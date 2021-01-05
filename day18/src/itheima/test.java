package itheima;

import java.util.Arrays;
import java.util.Random;

public class test {
    public static void main(String[] args) {

        int [] arr = new int[6];

        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {

            arr[i] = random.nextInt(33);
            Arrays.sort(arr);
            if (i==arr.length-1){
                arr[i] = random.nextInt(13);
            };

        }
        System.out.println(Arrays.toString(arr));
    }
}
