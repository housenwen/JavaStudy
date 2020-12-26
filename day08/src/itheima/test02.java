package itheima;
//⚫ 某公司季度和月份统计的数据如下：单位(万元) 
//第一季度：22,66,44 
//第二季度：77,33,88 
//第三季度：25,45,65
//第四季度：11,66,99
//实现思路：
//①：定义求和变量，准备记录最终累加结果
//②：使用二维数组来存储数据，每个季度是一个一维数组，再将4个一维数组装起来
//③：遍历二维数组，获取所有元素，累加求和
//④：输出最终结果
public class test02 {
    public static void main(String[] args) {
        int[][] arr = {{22,66,44},{77,33,88},{25,45,65},{11,66,99}};
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            // arr[i] 就是每一个一维数组
            for (int j = 0; j < arr[i].length; j++) {
//           打印数组的所有元素
                System.out.println(arr[i][j]);
//           数组的所有元素相加
                sum += arr[i][j];
            }
        }

        System.out.println("总和为："+sum);
    }
}
