import java.util.Scanner;

public class test02 {
    //    1. 创建键盘录入对象
//    2. 调用方法获取输入的数据
//    3. 将变量%2 如果 == 0 是偶数,否则是奇数
//    4. 输出结果
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字：");
        int num = scanner.nextInt();

        if (num % 2 == 0) {

            System.out.println("该数字是偶数");
        } else {
            System.out.println("该数字是奇数");

        }

    }
}
