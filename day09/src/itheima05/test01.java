package itheima05;



//1、字符串反转
//
//        举例：键盘录入”abc”
//
//        输出结果：”cba”
//
//        分析：
//
//             A:键盘录入一个字符串
//
//             B:写方法实现字符串的反转
//
//                    a:把字符串倒着遍历，得到的每一个字符拼接成字符串。
//
//                    b:把字符串转换为字符数组，然后对字符数组进行反转，最后在把字符数组转换为字符串
//
//             C:调用方法
//
//             D:输出结果
import java.util.Scanner;
public class test01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String s = scanner.nextLine();
        String s2 = Str(s);
        System.out.println(s2);
    }

    public static String Str(String a ){
        StringBuilder sb = new StringBuilder(a);
        sb.reverse();
        String s1 = sb.toString();
        return s1;
    }
}
