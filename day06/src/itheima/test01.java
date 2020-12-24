package itheima;

import java.util.Random;

//1.定义一个含有五个元素的数组,并为每个元素赋值,求数组中所有元素的最小值
//
//操作步骤：
//
//	1.定义5个元素数组
//
//	2.可以使用初始化数组的两种方式之一为数组元素赋值
//
//	3.遍历数组求数组中的最小值
public class test01 {
    public static void main(String[] args) {
//        int [] arr = {10,20,30,40,50};
        int[] arr = new int[5];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
          arr[i]   = random.nextInt(11);
            System.out.println(arr[i]);
        }
        int mini = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (mini>arr[i]){
                mini = arr[i];
            }
        }
        System.out.println("数组的最小值为："+mini);

    }
}
