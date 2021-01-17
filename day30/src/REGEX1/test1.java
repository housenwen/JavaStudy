package REGEX1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.TreeSet;

public class test1 {
    public static void main(String[] args) {
        Collection collection = new ArrayList<String>();

        Collections.addAll(collection,"abc","aaa","bbb","abc","ccc","abc");
        System.out.println(collection);

        ArrayList<String> list = new ArrayList<>(collection);
        System.out.println(list);
        TreeSet<String> set = new TreeSet<>(list);
        System.out.println(set);
        set.remove("abc");
        System.out.println(set);

    }
}
