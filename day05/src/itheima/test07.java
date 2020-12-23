package itheima;

import java.util.Random;

//本期大乐透中奖号码
//1.让普通号码不重复；2.让普通号码从大到小排列

public class test07 {
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println("本期大乐透的中奖号码是：");
        int x = 1;
        int y = 32;

        int num2 = random.nextInt(y - x + 1) + x;
        System.out.print("本期普通号码是：" + num2 + "\t");

        for (int i = 1; i <= 4; i++) {

            int num = random.nextInt(y - x + 1) + x;

            System.out.print(num + "\t");

        }

        System.out.println();

        int x1 = 1;
        int y1 = 12;
        int num1 = random.nextInt(y1 - x1 + 1) + x1;
        System.out.print("本期特别号码是：" + num1 + "\t");
        System.out.println();
    }
}
