package homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class test2 {
    public static void main(String[] args) throws Exception{
        Class c = student.class;
        Constructor con = c.getConstructor(String.class,int.class);
        student s = (student) con.newInstance("周植入",38);
        System.out.println(s);

        Method method = c.getMethod("setName", String.class);
        method.invoke(s,"周杰伦");
        System.out.println(s);

        Field agef = c.getDeclaredField("age");
        agef.setAccessible(true);
        agef.set(s,69);
        System.out.println(s);
    }
}
