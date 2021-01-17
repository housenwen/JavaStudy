package forEach;

import java.util.Scanner;

//todo 需求：请编写程序，校验键盘录入的电子邮箱是否合法，并测试
public class test7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入邮箱账号：");
        String data = sc.nextLine();
        boolean flag = validationEmail(data);
        if(flag){
            System.out.println("您输入的邮箱账号合法！");
        }else{
            System.out.println("您输入的邮箱账号不合法，请重新验证！");
        }
    }
    //校验键盘录入的电子邮箱是否合法
    public static boolean validationEmail(String data) {
        String regex = "\\w+@\\w+(.\\w+)+";
        return data.matches(regex);
    }
}

