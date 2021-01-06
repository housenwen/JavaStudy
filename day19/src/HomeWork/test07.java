package HomeWork;

import java.util.*;

public class test07 {
    public static void main(String[] args) {
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        Collection<Integer> list = new ArrayList<>();

        Collections.addAll(list,2,3,4,2,1,3,3,9);

        ArrayList<Integer> list1 =null;

        for (Integer integer:list){

            if (map.containsKey(integer)){
                list1 = map.get(integer);
            }else {
                list1=new ArrayList<>();
            }
            list1.add(integer);
            map.put(integer,list1);
        }
        System.out.println(map);
    }
}
