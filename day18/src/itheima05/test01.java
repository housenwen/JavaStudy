package itheima05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class test01 {
    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();

        Collections.addAll(list,1,2,3,4,5,6,7,8,9);
        System.out.println("增强for循環：");
        for (int a:list){
            System.out.println(a);
        }
//        Iterator地代器
        System.out.println("地代器遍歷");
        Iterator it  = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
//
        System.out.println("普通for循環");
        for (int i = 0; i < list.size(); i++) {

            System.out.println(list.get(i));

        }


    }
}
