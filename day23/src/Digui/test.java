package Digui;

public class test {
    public static void main(String[] args) {
        int num = 5;
        int sum = getSum(num);
        System.out.println(sum);
    }

    private static int getSum(int num) {

        if (num==1){
            return 1;
        }
        return num+getSum(num-1);
    }
}
