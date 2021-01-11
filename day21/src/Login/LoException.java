package Login;

import java.util.Scanner;

public class LoException extends Exception{

    public LoException() {
    }

    public LoException(String message) {
        super(message);
    }

    public static void Log() throws LoException {//static的作用？有无必要？？

        String s1="admin";
        String s2="12345";
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入姓名：");
        String name = sc.nextLine();
        System.out.println("请输入密码：");
        String passWord = sc.nextLine();
        if (!name.equals(s1)){
            throw new LoException("用户名错误");
        }
        if (!passWord.equals(s2)){
            throw new LoException("密码错误");
        }
        System.out.println("输入正确，欢迎登陆！");

    }

}
