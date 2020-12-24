package itheima;

import java.util.Random;
import java.util.Scanner;

//定义一个长度为20的数组，元素为20-40的随机数，
// 要求判断指定元素在数组中出现的次数，指定元素为键盘录入范围为20-40之间。
public class test {
    public static void main(String[] args) {
      Scanner scanner =  new Scanner(System.in);
      Random random =  new Random();

      int [] arr = new  int[20];

        for (int i = 0; i < arr.length; i++) {
            arr[i]=random.nextInt(40-20+1)+20;
            System.out.println(arr[i]);
        }
        System.out.println("请输入一个20-40之间的值：");
        int count = 0;
        int num = scanner.nextInt();

        for (int i = 0; i < arr.length; i++) {
            if (num == arr[i]){
                count++;
            }
        }
        System.out.println("判断指定元素在数组中出现的次数:"+count);

    }

}
