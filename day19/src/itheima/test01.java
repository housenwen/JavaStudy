package itheima;


import java.util.*;

public class test01 {
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        Collections.addAll(set,1,23,4,5,6,73,32,34,8);
        System.out.println(set);
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.addAll(set);
        Collections.shuffle(list);
        System.out.println(list);
        Collections.sort(list);
        System.out.println(list);
        TreeSet<Integer> treeSet = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        treeSet.addAll(list);
        System.out.println(treeSet);

    }
}
