import java.util.Scanner;

public class test05 {
    public static void main(String[] args) {
        int[] abc = new int[5];

        System.out.println("请输入五个整数：");
        int sum = 0;

        for (int i = 0; i < abc.length; i++) {
            System.out.println("请输入第"+(i+1)+"个整数：");
            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();
            abc[i] = num;
            sum += abc[i];

        }
        System.out.print("数组中的所有数为：");

        for (int i = 0; i < abc.length; i++) {

            System.out.print(abc[i]+"\t");

        }
        System.out.println();

        System.out.println("数组中的所有数的和为：" + sum);

    }
}
