package Proxy;

import java.util.*;

/**
 * 对Collection接口进行代理，以前的remove(Object obj)方法是删除集合中第一次出现的元素
 * (比如集合中有多个“abc”,调用remove(“abc”)后只会删除一个元素)。
 * 代理后，要求在调用remove(Object obj)方法后，
 * 能够删除集合中所有匹配的元素。【动态代理】
 */
public class test {
    public static void main(String[] args) {
        Collection<String> coll = new ArrayList<>();
        Collections.addAll(coll,"abc","bbb","ccc","ddd","abc","abc");
        System.out.println(coll);

//        coll.remove("abc");
//        System.out.println(coll);

//        ArrayList<String> list = (ArrayList<String>) coll;

        HashSet<String> set = new HashSet<>();

        for (String string:coll){
            set.add(string);
        }

//        for (int i = 0; i < list.size(); i++) {
//
//            if (list.get(i).equals("adc")){
//                list.remove(i);
//            }
//        }
//        System.out.println(list);

        System.out.println(set);
        set.remove("abc");
        System.out.println(set);
    }
}
