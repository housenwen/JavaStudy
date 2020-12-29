package itheima02;


import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择按钮1.注册 2.登陆 3.退出：");
        User[] users = new User[3];

        lo:
        while (true) {

            int num = scanner.nextInt();
            switch (num) {

                case 1:
                    System.out.println("注册");

                    for (int i = 0; i <= 3; i++) {
                        if (i == 3) {
                            System.out.println("数组已满禁止注册！");
                        } else {
                            users[i] = registe();
                        }
                    }

                    break;

                case 2:
                    System.out.println("登录");
                    User user = login();
                    for (int i = 0; i < users.length; i++) {

                        if (user.getName().equals(users[i].getName()) && user.getPassword().equals(users[i].getPassword())) {
                            System.out.println("登录成功，以下是您的个人信息：");
                            System.out.println(users[i].getName() + users[i].getPassword() + users[i].getBirthday() + users[i].getHob());

                            return;

                        } else {

                            System.out.println("用户名或密码错误！");

                        }

                    }

                    break;

                case 3:
                    System.out.println("退出");

                    break lo;

                default:
                    System.out.println("您输入有误，请重新输入");
                    break;

            }

        }
    }

    private static User login() {

        User user = new User();
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入用户名：");
        user.setName(scanner.next());
        System.out.println("请输入密码：");
        user.setPassword(scanner.next());

        return user;

    }

    private static User registe() {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        System.out.println("请输入用户信息：");

        System.out.println("用户名：");
        user.setName(scanner.next());
        System.out.println("密码：");
        user.setPassword(scanner.next());
        System.out.println("出生日期：");
        user.setBirthday(scanner.next());
        System.out.println("爱好：");
        user.setHob(scanner.next());

        return user;

    }
}
