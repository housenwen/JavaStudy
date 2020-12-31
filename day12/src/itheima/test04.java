package itheima;

import java.util.Scanner;

public class test04 {
    public static void main(String[] args) {
        String name = "admin";
        String password = "123456";
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("请输入用户名：");
            String username = scanner.next();
            System.out.println("请输入密码：");
            String userPassword = scanner.next();
            if (username.equals(name)&&userPassword.equals(password)){
                System.out.println("登录成功！");
//                break;
                System.exit(0);
            }
            else {
                System.out.println("用户名或密码错误，重新登录！");
            }
        }

    }
}
