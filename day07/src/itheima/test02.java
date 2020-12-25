package itheima;

//获取数组的最大值的方法
//思路：
//定义一个数组，用静态初始化完成数组元素初始化
//定义一个方法，用来获取数组中的最大值
//最值的认知和讲解我们在数组中已经讲解过了
//调用获取最大值方法，用变量接收返回结果
//把结果输出在控制台

public class test02 {
    public static void main(String[] args) {

        int[] brr = {10,20,30,40,50,60};
        Max(brr);
        System.out.println("另一串代码");

    }
    public static void Max(int[] arr){
        int max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (max<arr[i]);
            max = arr[i];
        }
        System.out.println("元素中的最大值:"+max);
    }
}
