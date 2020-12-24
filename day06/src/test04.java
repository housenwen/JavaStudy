import java.util.Scanner;
//裁判打分
public class test04 {
    public static void main(String[] args) {
        int[] abc = new int[6];
       Scanner sc = new Scanner(System.in);
        for (int i = 0; i < abc.length; i++) {
            System.out.println("请输入第"+(i+1)+"裁判打分");
            int num = sc.nextInt();
            if(num>=0&&num<=100){
                abc[i]=num;
            }else {

                System.out.println("您的打分有误，请重新输入：");
                i--;
            }
        }
        int max = abc[0];
        int mini = abc[0];
        for (int i = 0; i < abc.length; i++) {
            if (max<abc[i]){
                max = abc[i];
            }
        }
        System.out.println("选手的最高分为:"+max);
        for (int i = 0; i < abc.length; i++) {
            if (mini>abc[i]){

                mini = abc[i];

            }
        }
        System.out.println("选手的最小分为:"+mini);

        int sum = 0;
        for (int i = 0; i < abc.length; i++) {
            sum +=abc[i];

        }
        System.out.println("选手的总分是："+sum);

        int ping = (sum-max-mini)/4;

        System.out.println("选手的平均分是："+ping);
    }
}
