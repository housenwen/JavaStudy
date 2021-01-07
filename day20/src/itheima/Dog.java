package itheima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Dog {

    public static Map<String, ArrayList<Integer>> map = new HashMap<>();

    static {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        Collections.addAll(list1,1,2,3,4,5);
        Collections.addAll(list2,6,7,8,9,10);

        map.put("1班",list1);
        map.put("2班",list2);
    }
}
