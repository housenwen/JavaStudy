package itheima;

import java.util.Random;
import java.util.Scanner;

//3.按要求在main方法中完成以下功能：
//
//	a.   定义一个长度为5的int型数组arr，提示用户输入5个1-60之间的数字作为数组元素
//
//	b.  生成2-10（范围包含2和10）之间的随机数num
//
//	c.   遍历数组arr,筛选出数组中不是num倍数的元素并输出
//
//	PS：输入的数组元素范围包括1和60，不需要代码判断
public class test03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[5];
        System.out.println("请用户输入5个1-60之间的数字作为数组元素：");

        for (int i = 0; i < arr.length; i++) {
            System.out.println("请用户输入第" + (i + 1) + "个数字作为数组元素：");
            int num = scanner.nextInt();

            if (num >= 1 && num <= 60) {
                arr[i] = num;
            } else {
                System.out.println("请重新输入");
                i--;
            }
        }
        Random random = new Random();
        int num = random.nextInt(10 - 2 + 1) + 2;
        System.out.println("随机数为：" + num);
        System.out.println("该数组的值为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % num != 0) {
                System.out.println("不是num倍数的元素为：" + arr[i]);
            }
//            else {
//                System.out.println("数组元素是num的倍数:"+arr[i]);
//            }

        }
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] % num == 0) {
                System.out.println("数组元素是num的倍数:" + arr[j]);
            }

        }
    }
}
