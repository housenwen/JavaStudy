package forEach;

import java.util.Scanner;

public class test6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入身份证号：");
        String data = sc.nextLine();
        boolean flag = validationIDCard(data);
        if(flag){
            System.out.println("您输入的身份证号合法");
        }else{
            System.out.println("您输入的身份证号不合法！");
        }

    }
    //校验键盘录入的身份证号是否合法
    public static boolean validationIDCard(String data) {
        String regex = "(\\d{15}|\\d{18}|\\d{17}(\\d|X|x))";
        return data.matches(regex);
    }
}
