package itheima;

//定义 int 类型的变量 a,定义 int 类型的变量 b,
// 使用三元运算符判断如果 a 大于 b 返回 a 与 b 的和,
// 否则返回 a 与 b 的乘积,打印结果

public class test11 {
    public static void main(String[] args) {

        int a = 10;
        int b = 5;

        int c = (a>b)?a+b:a*b;

        System.out.println(c);

    }
}
