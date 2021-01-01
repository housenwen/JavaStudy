import java.util.Scanner;

public class test10 {
    public static void main(String[] args) {

        loop:
        while (true) {
            System.out.println("请输入您的星期数:");
            System.out.println("无需服务，按0退出");
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();

            switch (num) {

                case 0:
                    System.out.println("感谢您的使用，下次再见");
                    break loop;
                case 1:
                    System.out.println("跑步");
                    break;
                case 2:
                    System.out.println("游泳");
                    break;
                case 3:
                    System.out.println("健身");
                    break;
                case 4:
                    System.out.println("散步");
                    break;
                case 5:
                    System.out.println("爬山");
                    break;
                case 6:
                    System.out.println("健身房");
                    break;
                case 7:
                    System.out.println("休息");
                    break;
                default:
                    System.out.println("您输入有误，请重新输入");
                    break;

            }

        }
    }
}
