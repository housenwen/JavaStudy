package itheima02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Test02 {
    public static void main(String[] args) {
        Collection<String> list = new ArrayList<>();
        list.add("JavaEE企业级开发指南");
        list.add("Oracle高级编程");
        list.add("MySQL从入门到精通");
        list.add("Java架构师之路");

        Iterator it = list.iterator();

        while (it.hasNext()){
            System.out.println(it.next());
        }

        System.out.println("-----------------------------");
        Iterator it1 = list.iterator();
        while (it1.hasNext()){
            String str = (String) it1.next();
            if (str.length()<10){
                System.out.println(str);
            }
        }
        System.out.println("-----------------------------------");
        Iterator it2 = list.iterator();
        while (it2.hasNext()){

            String str1 = (String) it2.next();

            if (str1.contains("Java")){
                System.out.println(str1);
            }

        }
        System.out.println("--------------------------");

        Iterator it3 = list.iterator();
        while (it3.hasNext()){
            String str2 = (String) it3.next();

            if (str2.contains("Oracle")){
                it3.remove();
            }
        }
        System.out.println(list);
    }
}