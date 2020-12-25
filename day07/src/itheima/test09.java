package itheima;

import java.util.Random;

//定义功能，将数组中的0元素，使用1-10之间的随机数替换掉。
//
//	数组: int[] arr = {1,43,43,5,4,6,6,7,7,88,9,9,9,0,0,9};
public class test09 {
    public static void main(String[] args) {
        int[] arr = {1,43,43,5,4,6,6,7,7,88,9,9,9,0,0,9};
        a1(arr);
    }
    public static void a1(int [] arr){

      Random random =  new Random();

        for (int i = 0; i < arr.length; i++) {
            int num = random.nextInt(10-1+1)+1;
            if (arr[i]==0){
                arr[i]=num;
            }
        }
        System.out.println("替换后的数组元素为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+"\t");
        }

    }
}
