import java.util.Scanner;
public class test09 {
//    键盘录入；请输入1-100的数;
//    输入错误,重新输入
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true){

            System.out.println("请输入1-100的数：");
            int num = sc.nextInt();

            if (num>=1 && num<=100) {

                break;
            }else {
                System.out.println("请重新输入");
            }
          }
        }


    }

