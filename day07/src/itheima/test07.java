package itheima;
//定义功能，计算出数组中的最小值。
//
//数组: int[] arr = {1,43,43,5,4,6,6,7,7,88,9,9,9,0,0,9};
public class test07 {
    public static void main(String[] args) {
        int[] arr = {1,43,43,5,4,6,6,7,7,88,9,9,9,0,0,9};
        int min = a1(arr);
        System.out.println("最小值为："+min);
    }
    public static int  a1(int[] arr){
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (min>arr[i]){
                min = arr[i];
            }
        }
        return min;
    }
}
