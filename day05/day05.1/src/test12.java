import java.util.Random;
import java.util.Scanner;

//猜出90-100的随机数,猜数字游戏

public class test12 {
    public static void main(String[] args) {

        Random r = new Random();
        System.out.println("请输入一个90-100的数字");
        int num = r.nextInt(100-90+1)+90;

        while (true){

         Scanner sc =  new Scanner(System.in);
            int guess = sc.nextInt();
            if (guess ==num){

                System.out.println("您猜对了！");
                break;
            }else if (guess<num){
                System.out.println("你猜小了");
            }else if (guess>num){
                System.out.println("您猜大了");
            }else {
                System.out.println("您输入有误");
            }
        }
    }

}
