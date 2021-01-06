package itheima;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

//案例演示比较器排序(20,18,23,22,17,24,19):
public class test {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        Collections.addAll(set,2,364,74,12,34,56,3,5);
        System.out.println(set);

    }
}
