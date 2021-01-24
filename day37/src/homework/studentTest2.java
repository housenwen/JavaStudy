package homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class studentTest2 {
    public static void main(String[] args) throws Exception{
        Class clz = student.class;
        Object obj = clz.newInstance();
        System.out.println(obj);

        Constructor con = clz.getConstructor(String.class,int.class);
        Object obj2 = con.newInstance("张三",23);
        System.out.println(obj2);

        Method method = clz.getMethod("show");
        method.invoke(obj2);

    }
}