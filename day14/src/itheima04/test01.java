package itheima04;

import java.lang.reflect.Array;
import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        int[] arr = {10,27,8,5,2,1,3,55,88};
        int[] arr2 = new int[5];

        System.arraycopy(arr,2,arr2,0,5);

        System.out.println(Arrays.toString(arr2));
    }
}
