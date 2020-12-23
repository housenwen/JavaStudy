
public class test06 {
//定义一个 int 类型的变量,初始化值为 123,
// 求这个数的个位,十位,百位分别是多少,
// 输出结 果:123 的个位是 3,十位是 2,百位是 1
public static void main(String[] args) {
    int i = 123;
    int g = i%10;
    int s = i/10%10;
    int b = i/100%10;

    System.out.println(i+"个位是 "+g+",十位是 "+s+",百位是 "+b);

}
}
