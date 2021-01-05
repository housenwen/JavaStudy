package itheima02;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class test {
    public static void main(String[] args) {
        String[] nums = {"3.22","8.1","7","2.5","3.1","7.77","8.0","9.21"};
        BigDecimal sum = new BigDecimal("0");
        for (int i = 0; i < nums.length; i++) {
            String s = nums[i];
            BigDecimal bd = new BigDecimal(s);
            sum= sum.add(bd);
        }

        BigDecimal Avg = sum.divide(new BigDecimal(nums.length),2, RoundingMode.HALF_UP);

        System.out.println(Avg.doubleValue());
    }
}
