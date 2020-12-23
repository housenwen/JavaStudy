package itheima;

public class test02 {
    public static void main(String[] args) {

        int num = 0;
        int num1 = 0;
        int num2 = 0;

        for(int i=1;i<=100;i++){

            if ((i-1)%2==0){

                num1 +=i;
            }else {
                num2 +=i;
            }
            num+=i;
        }
        System.out.println("1到100的和是："+num);
        System.out.println("1到100的奇数和是："+num1);
        System.out.println("1到100的偶数和是："+num2);
    }
}
