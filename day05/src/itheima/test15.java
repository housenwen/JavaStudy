package itheima;
import java.util.Scanner;
public class test15 {
 /*   1.键盘录入学员张浩的3门课程(Java、SQL、Web)的成绩
    2.编写程序实现： (1)Java课程和SQL课程的分数只差
            (2)3 门课程的平均分
    3．程序运行格式：
    请输入Java的成绩：100
    请输入SQL的成绩：60
    请输入Web的成绩：80
            ---------------------------
    Java SQL Web
    100 60 80*/

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入Java的成绩:");
        System.out.println("请输入SQL的成绩:");
        System.out.println("请输入Web的成绩:");
        int java = scanner.nextInt();
        int SQL = scanner.nextInt();
        int Web = scanner.nextInt();

        int a = java-SQL;
        int b = SQL-java;

        int cha = (a>b)?a:b;

        double ping = (java+SQL+Web)/3;

        System.out.println("Java和SQL的成绩差："+cha);
        System.out.println("三门课的平均分是："+ping);


    }

}
