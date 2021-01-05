package itheima06;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class test01 {
    public static void main(String[] args) {
        ArrayList<Person> list = new ArrayList<>();
        Person p1 = new Person("張1", 21, 1.70);
        Person p2 = new Person("張2", 22, 1.70);
        Person p3 = new Person("張三", 23, 1.80);
        Person p4 = new Person("李四", 24, 1.60);
        Person p5 = new Person("王五", 25, 1.68);

        Collections.addAll(list, p1, p2, p3, p4, p5);

        Iterator<Person> it = list.iterator();
        Person maxP = list.get(0);
        Person minP = list.get(0);
        while (it.hasNext()) {
            Person p = it.next();
//            System.out.println(p);
            if (p.getHeight()>maxP.getHeight()){
                maxP = p;
            }
            if (minP.getHeight()>p.getHeight()){
                minP = p;
            }
        }
        System.out.println("最高的人："+maxP);
        System.out.println("最矮的人："+minP);

    }
}
