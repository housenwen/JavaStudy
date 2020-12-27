package itheima02;
//需求：按照帮助文档的使用步骤学习 Scanner 类的使用，并实现键盘录入一个字符串，最后输出在控制台
import java.util.Scanner;

public class Class {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请小心台阶，输入字符串：");
        String str = scanner.next();
        System.out.println(str);
    }
}
