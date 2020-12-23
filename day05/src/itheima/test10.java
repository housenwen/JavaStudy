package itheima;
import java.util.Random;
public class test10 {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("本期大乐透中奖号码：");

            int x = 1;
            int y = 32;
            int n1 = random.nextInt(y - x + 1) + x;
            int n2 = random.nextInt(y - x + 1) + x;
            int n3 = random.nextInt(y - x + 1) + x;
            int n4 = random.nextInt(y - x + 1) + x;
            int n5 = random.nextInt(y - x + 1) + x;

            System.out.print("本期普通号码是：");

            System.out.print(n1 + "\t");
            System.out.print(n2 + "\t");
            System.out.print(n3 + "\t");
            System.out.print(n4 + "\t");
            System.out.print(n5 + "\t");

            System.out.println();

            int x1 = 1;
            int y1 = 12;
            int num1 = random.nextInt(y1 - x1 + 1) + x1;
            System.out.print("本期特别号码是：" + num1 + "\t");
            System.out.println();

    }

}
