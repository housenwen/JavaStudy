package itheima;
//4、定义功能，计算出数组中指定元素出现的次数。
//
//	数组: int[] arr = {1,43,43,5,4,6,6,7,7,88,9,9,9,0,0,9};
//
//	指定元素: int x = 9;

public class test08 {
    public static void main(String[] args) {
        int[] arr = {1,43,43,5,4,6,6,7,7,88,9,9,9,0,0,9};
        int count = a1(arr);
        System.out.println("数组中9出现的次数为："+count);

    }
    public static int a1(int[] arr){
        int count = 0;
        int num = 9 ;

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]==num){
                count++;
            }
        }
        return count;
    }
}
