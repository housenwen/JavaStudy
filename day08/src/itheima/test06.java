package itheima;
//4、定义方法，参数为整数数组与指定元素，判断数组中有多少个能整除指定元素的，在主方法中输出。
// 数组int[] arr = {12,43,45,76,78,98,23,21,34,56};指定元素为2；
public class test06 {
    public static void main(String[] args) {
        int[] arr = {12,43,45,76,78,98,23,21,34,56};
        int a = 2;
        int num = Get2(arr,a);
        System.out.println("在数组中能整除"+a+"的元素个数为："+num);
    }
    public static int Get2(int[] arr,int a){
        int count= 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]%2==0){
                count++;
            }
        }
        return count;
    }
}
