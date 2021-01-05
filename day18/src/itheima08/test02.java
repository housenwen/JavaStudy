package itheima08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class test02 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a","f","b","c","a","d");
        System.out.println(list);
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {

            set.add(list.get(i));

        }
        System.out.println(set);
        list.clear();
        System.out.println(list);

        for (String s:set){
            list.add(s);
        }
//        list.addAll(set);
        System.out.println(list);
    }
}
