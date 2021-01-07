package itheima;


import java.util.*;

public class test05 {
    public static void main(String[] args) {

        final Map<String, ArrayList<Integer>> map = Dog.map;

        System.out.println(map);

        Set<String> set = map.keySet();
        for (String s:set){
            System.out.println(s+"="+map.get(s));
        }

        ArrayList<Integer> list = map.get("1班");
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
            i--;
        }
        System.out.println(map);

        Collections.addAll(list, 11, 22, 33, 44, 55);
        System.out.println(map);


    }
}
