package homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class studentTest {

    public static void main(String[] args) throws Exception {

        Class clz = student.class;
        Constructor con = clz.getConstructor(String.class,int.class);
        student stu = (student) con.newInstance("张三",13);
        Method method = clz.getMethod("show");

        method.invoke(stu);

        Method toString = clz.getMethod("toString");
        String s = (String) toString.invoke(stu);
        System.out.println(s);

    }
}