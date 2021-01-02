package itheima03;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Object[] ints = list.toArray();

        System.out.println(Arrays.toString(ints));


    }
}
