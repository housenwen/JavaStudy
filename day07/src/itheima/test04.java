package itheima;
//设计一个方法用于获取数组中元素的最大值，和最小值
//思路：
//定义一个数组，用静态初始化完成数组元素初始化
//定义一个方法，用来获取数组中的最大值和最小值
//方法中定义一个数组，将最大值和最小值存入数组中，并将整个数组返回
//调用该方法，将数组中的最大值和最小值取出进行打印
//return 语句只能带回一个结果

public class test04 {
    public static void main(String[] args) {
        int[] brr = {10,20,30,40,50,60,40};

        int[] Get = getNum(brr);

        System.out.println("最小值："+Get[0]);
        System.out.println("最大值："+Get[1]);


    }
    public static int[] getNum(int[] arr){
        int max =arr[0];
        int min =arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (max<arr[i]){
                max = arr[i];
            }else if (min>arr[i]){
                min = arr[i];
            }
        }
        int[] get = {min,max};

        return get;
    }
}
