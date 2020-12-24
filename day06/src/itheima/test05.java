package itheima;

import java.util.Random;
import java.util.Scanner;

//5.定义一个长度为20的数组，元素为20-40的随机数，
//要求判断指定元素在数组中出现的次数，指定元素为键盘录入范围为20-40之间。
public class test05 {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int[] arr = new int[20];
        System.out.println("数组的随机元素为：");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(40 - 20 + 1) + 20;
            System.out.println(arr[i]);
        }
        int count = 0;
        System.out.println("请输入一个20-40的数字：");
        int num = scanner.nextInt();
        for (int i = 0; i < arr.length; i++) {

            if (num==arr[i]){
                count++;
            }
        }
        System.out.println("指定元素在数组中出现的次数:" + count);
    }
}
