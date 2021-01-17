package Fanshe;

/**
 * 1. 现有集合：ArrayList<Integer>list = new ArrayList();
 * 2. 利用反射机制在这个泛型为Integer的ArrayList中存放一个String类型的对象。
 */

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class test {
    public static void main(String[] args) throws Exception{
        ArrayList<Integer> list = new ArrayList<>();
        Collections.addAll(list,111,222,333);
        Class c = list.getClass();
        Method addM = c.getDeclaredMethod("add",Object.class);
        addM.invoke(list,"aaaa");
        System.out.println(list);
    }
}
