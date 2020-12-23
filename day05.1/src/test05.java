import java.util.Scanner;
//键盘录入自己的姓名(String),年龄(int),身高(int)保存到对应的变量中,
//输出结果如:我的姓名是 张三,年龄 25 岁,身高 180CM
//(提示姓名是 String 类型,需要使用 Scanner 的 next()方法.)
public class test05 {
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
//        Scanner scanner2 = new Scanner(System.in);
//        Scanner scanner3 = new Scanner(System.in);
        System.out.println("请输入您的姓名：");
        System.out.println("请输入您的年龄：");
        System.out.println("请输入您的身高：");
        String name = scanner1.next();
        int age = scanner1.nextInt();
        int height = scanner1.nextInt();
        System.out.println("您的姓名是："+name+",您的年龄是："+age+"岁,您的身高："+height+"cm");

    }
}
