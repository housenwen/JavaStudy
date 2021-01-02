package itheima06;

import java.util.Scanner;

//	请用户从控制台输入一个“Email地址”，
//	程序接收后判断此Email地址中是否包含@符号和.符号，
//	如果全部包含，打印：合法，否则打印：不合法的Email地址。
public class test01 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个邮箱地址：");
        String mailBox = sc.next();

        if (mailBox.contains("@")&&mailBox.contains(".")){

            System.out.println("合法的邮箱地址");

        }else {
            System.out.println("不合法的邮箱地址");
        }


    }
}
