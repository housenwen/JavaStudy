package itheima03;

import java.util.Arrays;
import java.util.Scanner;

//需求：定义一个方法，把 int 数组中的数据按照指定的格式拼接成一个字符串返回，调用该方法，并在控制台
//输出结果。例如，数组为int[] arr = {1,2,3}; ，执行方法后的输出结果为：[1, 2, 3]
//思路：
//① 定义一个 int 类型的数组，用静态初始化完成数组元素的初始化
//② 定义一个方法，用于把 int 数组中的数据按照指定格式拼接成一个字符串返回。返回值类型 String，参数列表 int[] arr
//③ 在方法中用 StringBuilder 按照要求进行拼接，并把结果转成 String 返回
//④ 调用方法，用一个变量接收结果
//⑤ 输出结果
public class test05 {
    public static void main(String[] args) {
        int[] arr = {1,2,3};
        System.out.println(Arrays.toString(arr));
        String s3 =  string(arr);
        System.out.println(s3);

    }
    public static String string(int[] arr){

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < arr.length; i++) {

            if(i==arr.length-1){
               sb.append(arr[i]+"]");
            }
            else {
                sb.append(arr[i]+",");
            }
        }

        return sb.toString();
    }
}
