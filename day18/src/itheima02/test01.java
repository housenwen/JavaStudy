package itheima02;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.math.RoundingMode.*;

public class test01 {
    public static void main(String[] args) {

        Collection<Double> list = new ArrayList();
        list.add(88.5);
        list.add(39.2);
        list.add(77.1);
        list.add(56.8);
        list.add(89.0);
        list.add(99.0);
        list.add(59.5);

        printArray_list(list);
        printArray_UnPass(list);
        printArray_UnPass_Number(list);
        printArray_list_Average(list);
        printArray_list_MaxNum(list);
        printArray_list_MaxNum2(list);


    }

    private static void printArray_list_MaxNum2(Collection<Double> list) {
        double max = Collections.max(list);
        System.out.println("第二种方式最大值为："+max);
    }

    private static void printArray_list_MaxNum(Collection<Double> list) {
//        double maxValue = (double) ((List)list).get(0);
        double max = (double) ((List) list).get(0);
        for (Double d : list) {

            if (max<d){
                max =d;
            }
        }
        System.out.println("最大值为:"+max);
    }

    private static void printArray_list_Average(Collection<Double> list) {
        Double sum = Double.valueOf(0);
        for (Double d : list) {
            sum += d;
        }
        BigDecimal sum1 = new BigDecimal(sum);
        BigDecimal avg = sum1.divide(new BigDecimal(list.size()), 2, RoundingMode.HALF_UP);

        System.out.println("平均值为：" + avg);
    }


    private static void printArray_UnPass_Number(Collection<Double> list) {
        int count = 0;
        for (Double d : list) {
            if (d < 60) {
                count++;
            }
        }
        System.out.println("不合格的数量为：" + count);
    }

    private static void printArray_UnPass(Collection<Double> list) {
        for (Double d : list) {
            if (d < 60) {
                System.out.println("不及格的分数为：" + d);
            }
        }
    }

    private static void printArray_list(Collection list) {
        for (Object d : list) {
            System.out.println(d);
        }
    }
}
