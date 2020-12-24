import java.util.Scanner;

public class
test07 {
    public static void main(String[] args) {
        int[] brr =new  int[5];
     Scanner scanner =  new Scanner(System.in);

        for (int i = 0; i < brr.length; i++) {
            System.out.println("请输入第"+(i+1)+"个整数：");
            brr[i] = scanner.nextInt();
        }
        int max = brr[0];
        int sum = 0;
        System.out.println("该数组的数字为：");

        for (int i = 0; i < brr.length; i++) {

            System.out.println(brr[i]);

            if (max<brr[i]){
                max=brr[i];
            }
            sum += brr[i];
        }
        System.out.println("数组的最大值是："+max);
        System.out.println("数组的总和是："+sum);

    }
}
