package itheima;

public class test01 {
    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9};
        int num = 10;
        showArray(arr, num);
        System.out.println("arr[2]的结果是:"+arr[2]);
        System.out.println("num的结果是："+num);
    }
    private static void showArray(int[] arr, int num) {
        arr[2] = 6;
        num = 1;
        System.out.println("num的结果是："+num);
    }
}
