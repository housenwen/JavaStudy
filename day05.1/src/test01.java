import java.util.Scanner;

//键盘录入一个 int 类型的数据,使用三元运算符判断这个数是奇数还是偶数

public class test01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入数字：");
        int num = scanner.nextInt();

//        if(num%2==1){
//            System.out.println("该数字是奇数");
//        }else if (num%2==0){
//
//            System.out.println("该数字是偶数");
//        }else{
//
//            System.out.println("输入有误");
//        }

        String a = "偶数";
        String b = "奇数";
        String c = (num % 2 == 0) ? a : b;

        System.out.println(c);


    }
}
