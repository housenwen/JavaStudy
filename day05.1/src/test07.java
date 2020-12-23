//键盘录入一个长方形的长和宽(长和宽为 int 类型),计算长方形的面积和周长
import java.util.Scanner;
public class
test07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入长方形的长和宽");
        int chang = sc.nextInt();
        int kuan = sc.nextInt();

        int s = chang*kuan;
        int d = (chang+kuan)*2;

        System.out.println("长方形的面积是："+s+"周长"+d);

    }
}
