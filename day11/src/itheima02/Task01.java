package itheima02;

import java.util.Scanner;

public class Task01 {
    private static User[] userArray = new User[3];
    private static int index = 0;// 当前数组可存储的位置，如果等于userArray.length -1，说明用户已满，禁止注册

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1.注册     2.登陆     3.退出");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    regist();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    System.out.println("谢谢使用，拜拜！");
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    // 注册
    private static void regist() {
        // 验证用户是否已满
        if (index == userArray.length) {
            System.out.println("用户已满，禁止注册！");
            return;
//            break;
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String userName = sc.next().trim();
        System.out.println("请输入登录密码：");
        String pwd = sc.next().trim();
        System.out.println("请输入生日：");
        String birthDay = sc.next().trim();
        System.out.println("请输入爱好(多个用逗号隔开)：");
        String hobby = sc.next().trim();

        // 验证通过，写入到数组
        User user = new User(userName, pwd, birthDay, hobby);
        userArray[index] = user;
        // 将索引增1，以备下一次存储
        index++;
        // 成功提示
        System.out.println("注册成功！");
    }

    // 登录
    private static void login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入登录名：");
        String userName = sc.next();
        System.out.println("请输入密码：");
        String pwd = sc.next();

        // 验证
        for (int i = 0; i < userArray.length; i++) {
            if (userName.equals(userArray[i].getName()) && pwd.equals(userArray[i].getPassword())) {
                System.out.println("登录成功，以下是您的个人信息：");
                System.out.println("用户名：" + userArray[i].getName() + " , 密码：" + userArray[i].getPassword() + " ,出生日期：" + userArray[i].getBirthday()
                        + " ,爱好：" + userArray[i].getHob());
                return;
            }
        }
        System.out.println("登录失败，用户名或密码错误！");

    }

}
