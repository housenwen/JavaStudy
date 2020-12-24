import java.util.Scanner;

public class test03 {
//    键盘录入6个裁判的打分，去掉最低最高分，计算平均分
    public static void main(String[] args) {

        int[] arr = new int[6];

        Scanner sc = new Scanner(System.in);


        for (int i = 0; i < arr.length; i++) {
            System.out.println("请输入第" + (i + 1) + "的裁判打分：");
            int num = sc.nextInt();
            if (num >= 0 && num <= 100) {
                arr[i] = num;
            } else {
                System.out.println("您的输入有误");
                i--;
            }
        }
        int num1 = arr[0];
        int num2 = arr[0];
        for (int i = 0; i < arr.length; i++) {
//            求最小值
            if (num1 > arr[i]) {
                num1 = arr[i];
            }
//            求最大值
            else if (num2 < arr[i]) {
                num2 = arr[i];
            }
        }
        System.out.println("裁判打分最低分" + num1);
        System.out.println("裁判打分最高分" + num2);
//数组求和
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
//按规则求数组的平均值
        double ping = (sum - num1 -num2)/(arr.length-2);
        System.out.println("该选手的平均分是："+ping);
    }
}
