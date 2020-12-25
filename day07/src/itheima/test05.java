package itheima;
//1、定义功能，遍历数组中的所有元素，打印并以逗号( , )隔开每个元素。
//
//有数组： int[] arr = {1,2,3,4,5,6,7,8,9};
//
//分析：
//
//	参数： 需要。就是需要遍历的那个数组。int[]类型。
//
//	返回值： 不需要，因为传递是地址。不管哪个方法做操作，最终都是操作的同一个数         组中的元素。
//
//	1、使用循环提供数组的下标，结合数组名操作每一个数组空间。
//
//	2、对获取的每个元素做判断，是否为最后一个，如果是直接打印元素
//
//	3、如果不是，打印元素的同时，拼接一个逗号。
public class test05 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        PrintMarry(arr);

    }
    public static void PrintMarry(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length-1){
                System.out.println(arr[i]+"。");
            }
            else {
                System.out.print(arr[i]+",");
            }
        }
    }
}
