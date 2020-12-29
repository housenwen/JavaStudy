package itheima03;

import java.util.ArrayList;
import java.util.Arrays;

public class test01 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String s1 = "test";
        String s2 = "张三";
        String s3 = "李四";
        String s4 = "test";
        String s5 = "test";

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

//        System.out.println(Arrays.toString(list));

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            System.out.println(s);
        }
        System.out.println("----------------------------------------");
        list.remove(3);
        list.remove("test");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);

            System.out.println(s);
        }

    }
}
