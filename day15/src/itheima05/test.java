package itheima05;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class test {

    public static void main(String[] args) {

        //声明数组
        double[] arr = {0.1, 0.2, 2.1, 3.2, 5.56, 7.21};

        Sum(arr);
        avg(arr);

    }

    private static void Sum(double[] arr) {

        BigDecimal bd = new BigDecimal("0");

        for (int i = 0; i < arr.length; i++) {
            bd = bd.add(BigDecimal.valueOf(arr[i]));
        }

        System.out.println("总和："+bd);

    }

    private static void avg(double[] arr) {
        BigDecimal bd = new BigDecimal("0");

        for (int i = 0; i < arr.length; i++) {
            bd = bd.add(BigDecimal.valueOf(arr[i]));
        }
        BigDecimal bd2 = new BigDecimal("0");
        bd2 = bd.divide(BigDecimal.valueOf(arr.length),2, RoundingMode.HALF_UP);

        System.out.println("平均值："+bd2);


    }
}
