import java.util.Random;
import java.util.Scanner;

public class test08 {
    public static void main(String[] args) {
      Scanner scanner =  new Scanner(System.in);
        System.out.println("使用键盘录入输入一个数字：");
      int num = scanner.nextInt();
      int [] arr = new int [num];
        Random random = new Random();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(99-22+1)+22;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        int max = arr[0];
        int mini = arr[0];
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (max<arr[i]){

                max = arr[i];

            }else if(mini>arr[i]){

                mini= arr[i];
            }
            sum += arr[i];
        }
        double ping = sum/arr.length;
        System.out.println("数组中的最大值："+max);
        System.out.println("数组中的最小值："+mini);
        System.out.println("数组和值："+sum);
        System.out.println("数组平均值："+ping);

    }

}
