package itheima04;

import java.util.Arrays;

public class test02 {
    public static void main(String[] args) {
        //创建数组
        int[] arr = {55, 77, 0, 88, 22, 44, 33};

        //后四个元素前移
        System.arraycopy(arr, 3, arr, 2, 4);

        //最后一位置为0
        arr[arr.length - 1] = 0;
        //打印结果
        System.out.println(Arrays.toString(arr));

    }
}
