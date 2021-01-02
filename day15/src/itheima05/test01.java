package itheima05;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class test01 {
    public static void main(String[] args) {

        ArrayList<Student> list  = new ArrayList<>();

        Student s1 = new Student("张三","男",20,79.5);
        Student s2 = new Student("李四","女",21,80.2);
        Student s3 = new Student("王五","男",22,77.9);
        Student s4 = new Student("周六","男",20,55.8);
        Student s5 = new Student("赵七","女",21,99.9);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        BigDecimal bd = new BigDecimal("0");

        for (int i = 0; i < list.size(); i++) {

          bd =  bd.add(BigDecimal.valueOf(list.get(i).getSorce()));
        }

        System.out.println(bd);

        double avg = bd.divide(BigDecimal.valueOf(5),2, RoundingMode.HALF_UP).doubleValue();

        System.out.println(avg);

    }
}
