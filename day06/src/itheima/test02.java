package itheima;

import java.util.Random;
//2．需求：求出数组中索引与索引对应的元素都是奇数的元素
//
//分析：
//
//	1、遍历数组
//
//	2、判断索引是否是奇数（索引 % 2 != 0）
//
//	3、判断索引对应的元素是否是奇数(arr[索引] % 2 != 0)
//
//	4、满足条件输出结果
public class test02 {
    public static void main(String[] args) {
       Random random = new Random();
       int [] arr = new int[5];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(10);
            System.out.println(arr[i]);

            }
        System.out.println("数组中索引与索引对应的元素都是奇数的元素:");
        for (int i = 0; i < arr.length; i++) {
            if (i%2==1||arr[i]%2==1){
                System.out.println(arr[i]);
            }
        }
        }
        
    }

