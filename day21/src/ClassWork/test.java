package ClassWork;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        //分别从控制台接收两个整数
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个数字：");
        int num1 = sc.nextInt();

        System.out.println("请输入第二个数字：");
        int num2 = sc.nextInt();

        try
        {

            int dvi = num1/num2;
            System.out.println("第一个数/第二个数="+dvi);

        }catch (ArithmeticException e){

            if(num2==0){
                System.out.println("第二个数不能为0");
            }

        }
    }
}
