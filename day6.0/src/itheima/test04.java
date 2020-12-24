package itheima;

import java.util.Scanner;

//键盘录入学生考试成绩，
// 判断学生等级: 90-100
// 优秀 80-90
// 好 70-80
// 良 60-70
// 及格 60 以下 不及格
public class test04 {
    public static void main(String[] args) {
     Scanner scanner =   new Scanner(System.in);
        System.out.println("请输入学生考试成绩:");
     int n = scanner.nextInt();

        if(n>90&&n<=100){
            System.out.println("该学生成绩优秀");
        }else if (n>80&&n<=90){
            System.out.println("该学生成绩好");
        }else if (n>70&&n<=80){
            System.out.println("该学生成绩良");
        }else if(n>60&&n<=70){
            System.out.println("该学生成绩及格");
        }else if(n>=0&&n<60){
            System.out.println("该学生成绩不及格");
        }else {
            System.out.println("您输入有误！");
        }

    }
}

