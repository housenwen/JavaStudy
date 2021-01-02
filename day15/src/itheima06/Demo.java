package itheima06;

import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        //创建Scanner对象
        Scanner sc = new Scanner(System.in);

        //接收用户输入
        System.out.println("请输入Java源码文件名（例如 Test.java）：");
        String filename = sc.nextLine();

        //判断是否以.java结尾
        boolean result = filename.endsWith(".java");
        System.out.println("是否以.java结尾：" + result);

        //获取.符号的索引位置
        int index = filename.lastIndexOf(".");
        System.out.println(".符号的索引位置：" + index);
    }
}