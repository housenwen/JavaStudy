import java.util.Scanner;
public class test03 {

//    键盘录入一个学生成绩(int 类型),如果成绩大于等于 60 输出”及格”,如果成绩小于 60 输出”不 及格”

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入您的成绩：");
        int num = scanner.nextInt();

        if (num>=60&&num<=100){

            System.out.println("您的成绩及格");
        }else if (num>=0&&num<=60){
            System.out.println("您的成绩不及格");

        }else {

            System.out.println("您的输入有误");

        }

    }
}

