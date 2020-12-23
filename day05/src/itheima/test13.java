package itheima;
import java.util.Scanner;
//小明左、右手中分别拿两张纸牌（比如：黑桃 10 和红桃 8，数字 10 和 8 可通过键盘录入），
//
// 要求编写代码交换小明手中的牌

public class test13 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入两个数字：");

        int left = scanner.nextInt();

        int right = scanner.nextInt();

        int temp = left;

        left = right;

        right = temp;

        System.out.println(left+" "+right);

    }
}
