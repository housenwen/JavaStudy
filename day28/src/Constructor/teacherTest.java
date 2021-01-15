package Constructor;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class teacherTest {
    //todo 反射操作静态方法
    @Test
    public void test04() throws Exception{
    // 获得Class对象
        Class c = teacher.class;
        // 根据方法名获得对应的公有成员方法对象
        Method method = c.getDeclaredMethod("eat", String.class);
        // 通过method执行对应的方法
        method.invoke(null,"蛋炒饭");
    }
    /**
     * Method[] getMethods();
     * 获得类中的所有成员方法对象，返回数组，只能获得public修饰的且包含父类的
     * Method[] getDeclaredMethods();
     * 获得类中的所有成员方法对象，返回数组,只获得本类的，包含private修饰的
     */
    public void test03() throws Exception{
        // 获得Class对象
        Class c = teacher.class;
        // 获得类中的所有成员方法对象，返回数组，只能获得public修饰的且包含父类的
        // Method[] methods = c.getMethods();
        // 获得类中的所有成员方法对象，返回数组,只获得本类的，包含private修饰的
        Method[] methods = c.getDeclaredMethods();
        for (Method method:methods){
            System.out.println(method);
        }

    }
    /**
       Method getDeclaredMethod(String name,Class...args);
           * 根据方法名和参数类型获得对应的构造方法对象，
    */
    public void test02() throws Exception{
        // 获得Class对象
        Class c = teacher.class;
        // 根据Class对象创建老师对象
        teacher tea = (teacher) c.newInstance();
        // 获得sleep方法对应的Method对象
        Method m = c.getDeclaredMethod("sleep");
    }

}