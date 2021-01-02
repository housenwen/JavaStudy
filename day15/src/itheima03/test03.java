package itheima03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class test03 {
    public static void main(String[] args) {

        String[] strings = {"我","是","中","国","人"};
        System.out.println(Arrays.toString(strings));

        for (String s:strings){
            System.out.println(s);
        }

        System.out.println("---------------------------");
        Collection<String> coll = new ArrayList<>();

        coll.add("张三");
        coll.add("李四");
        coll.add("王二麻子");
        coll.add("罩得住");

        for (String s1:coll){
            System.out.println(s1);
        }

    }
}
