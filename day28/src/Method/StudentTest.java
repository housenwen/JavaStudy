package Method;

import com.sun.xml.internal.ws.api.model.MEP;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class StudentTest {
    @Test
    public void test04() throws Exception{
        Class c = Student.class;
        Method method = c.getDeclaredMethod("eat", String.class);
        method.setAccessible(true);
        Student stu =(Student) c.newInstance();
        method.invoke(stu,"西红柿炒蛋");
    }
    @Test
    public void test03() throws Exception{
        Class c = Student.class;
        Method[] methods = c.getDeclaredMethods();
        for (Method method:methods){
            method.setAccessible(true);
            System.out.println(method);
        }
    }
    @Test
    public void test02() throws Exception{
        Class c = Student.class;
        Student stu =(Student) c.newInstance();
        Method method = c.getDeclaredMethod("sleep");
        method.setAccessible(true);
        method.invoke(stu);
    }
    @Test
    public void test01() throws Exception{
        Class c = Student.class;
        Student stu =(Student) c.newInstance();
        Method method = c.getMethod("study", int.class);
        method.invoke(stu,10);
    }

}