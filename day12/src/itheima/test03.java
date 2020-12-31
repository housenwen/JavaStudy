package itheima;

import java.lang.reflect.Array;
import java.util.Arrays;

public class test03 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        getArr(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void getArr(int[] arr) {

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < arr.length; i++) {
            if (i==arr.length-1){
                sb.append(arr[i]+"]");
            }else {
                sb.append(arr[i]+",");
            }
        }
        System.out.println(sb.toString());
    }
}
