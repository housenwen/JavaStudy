package regex;

import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入你的QQ号码：");
        String qq = sc.next();

        System.out.println(checkQQ(qq));
    }
    //我们自己编写代码，验证QQ号码
    private static boolean checkQQ(String qq) {
        //1.验证5--15位
        if(qq.length() < 5 || qq.length() > 15){
            return false;
        }
        //2.必须都是数字；
        for(int i = 0;i < qq.length() ; i++){
            char c = qq.charAt(i);
            if(c < '0' || c > '9'){
                return false;
            }
        }
        //3.首位不能是0；
        char c = qq.charAt(0);
        if(c == '0'){
            return false;
        }
        return true;//验证通过
    }

}
