package itheima;
//使用 for 循环,求出 1-100 之间的奇数之和.
public class test05 {
    public static void main(String[] args) {
        int sum = 0;
        int sum1 = 0;
        int sum2 = 0;
        for (int i = 1; i <= 100; i++) {
            sum1 +=i;
            if (i%2==1){
                sum +=i;
            }else {
                sum2 +=i;
            }
        }
        System.out.println("1-100的奇数总数："+sum);
        System.out.println("1-100的偶数总数："+sum2);
        System.out.println("1-100的总数："+sum1);
    }
}
