package homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 需求：
 *
 * 1. 现有集合：ArrayList<Integer>list = new ArrayList();
 * 2. 利用反射机制在这个泛型为Integer的ArrayList中存放一个String类型的对象。
 */
public class test1 {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList<Integer> list = new ArrayList();
        Collections.addAll(list,1111,2222,3333);
        Class c = list.getClass();
        Method add = c.getMethod("add", Object.class);
        add.invoke(list,"aaaaa");
        System.out.println(list);
    }
}
