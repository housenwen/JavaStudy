package itheima;

public class test02 {
    public static void main(String[] args) {
        int sum = getSum(12,34,5,3,21,55);
        System.out.println(sum);
    }

    private static int getSum(int... arr) {

        int sum = 0;
        for (int i:arr){
            sum+=i;
        }

        return sum;
    }
}
