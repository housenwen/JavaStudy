package itheima02;

import java.util.Scanner;

public class test03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = "admin";
        String password = "123456";

        for (int i = 1; i <= 3; i++) {

        System.out.println("请输入用户名：");
        String scUser = scanner.nextLine();
        System.out.println("请输入密码：");
        String scPass = scanner.nextLine();

        if (name.equals(scUser)&&password.equals(scPass)){
            System.out.println("登录成功");
            break;
        }else {
            if(i ==3 ){
                System.out.println("您今天输入已达到上限！");
            }
            System.out.println("登录失败,"+"您还有"+(3-i)+"次机会");
        }
    }
}
}
