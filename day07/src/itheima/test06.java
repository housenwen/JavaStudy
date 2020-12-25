package itheima;
//2、定义功能完成数组的求和。一个功能求偶数和，一个功能求奇数和。一个功能求总和。
//
//数组： int[] arr = {1,2,3,4,5,6,7,8,9};
//
//分析：
//
//	参数： 需要求和的数组。 arr
//
//	返回值：就是数组中所有元素的和值。 int类型
//
//	1、因为要求3种不同的和值，因此需要定义3个方法。
//
//	2、使用循环提供出数组中的所有下标
//
//	3、求总和直接定义变量，将获取的每个值保存到变量空间中。
//
//	4、求偶数或奇数和值时，需要对取出的每个数据判断，满足条件则处理。
//
//	5、返回最后的数据。
public class test06 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,10};
        int num = a1(arr);
        int num1= a2(arr);
        int num2 = a3(arr);
        System.out.println("偶数和为："+num);
        System.out.println("奇数和为："+num1);
        System.out.println("数组和为："+num2);

    }

    public static int a1(int[] arr){
        int num = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]%2==0){
                num +=arr[i];
            }
        }
        return num;
    }
    public static int a2(int[] arr){
        int num = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]%2!=0){
                num +=arr[i];
            }
        }
        return num;

    }
    public static int a3(int[] arr){
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
                num +=arr[i];
        }
        return num;
    }

}
