import java.util.Scanner;
public class test04 {
//    1. 创建键盘录入对象
//    2. 调用方法获取输入的成绩
//    3. 使用三元运算符如果成绩大于等于 60 返回"及格",否则返回不"及格"
//    4. 输出结果
public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("请输入您的成绩：");
    int num = scanner.nextInt();

    String a = "您的成绩及格";
    String b = "您的成绩不及格";
    String c = (num>=60&&num<=100)?a:b;

    System.out.println(c);

}
}
