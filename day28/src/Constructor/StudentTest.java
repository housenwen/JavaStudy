package Constructor;

import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.*;

public class StudentTest {
     /* todo Constructor getConstructor(Class... parameterTypes)
            根据参数类型获得对应的Constructor对象
     */
    @Test
    public void test01() throws Exception{
        // 获得Class对象
        Class c = Student.class;
        // 获得无参数构造方法对象
        Constructor con = c.getConstructor();
        // 根据构造方法创建对象
        Object obj = con.newInstance();
        System.out.println(obj);

        // 获得有参数的构造方法对象
        Constructor con2 = c.getConstructor(String.class,String.class,int.class);
        con2.setAccessible(true);
        Object obj2 = con2.newInstance("jack","男",18);
        System.out.println(obj2);

    }
    /*todo
           Constructor getDeclaredConstructor(Class... parameterTypes)
               根据参数类型获得对应的Constructor对象
        */
    @Test
    public void test02() throws Exception{
        // 获得Class对象
        Class c = Student.class;
        // 获得两个参数构造方法对象
        Constructor con = c.getDeclaredConstructor(String.class,String.class);
        // 取消权限检查(暴力反射)
        con.setAccessible(true);
        // 根据构造方法创建对象
        Object obj = con.newInstance("rose","女");
        System.out.println(obj);

    }
    /*todo
       Constructor[] getConstructors()
           获得类中的所有构造方法对象，只能获得public的
     todo
      Constructor[] getDeclaredConstructors()
            获得类中的所有构造方法对象，包括private修饰的
    */
    @Test
    public void test03() throws Exception{
        // 获得Class对象
        Class c = Student.class;
        //  获得类中的所有构造方法对象，只能获得public的
//        Constructor[] cons = c.getConstructors();
        // 获取类中所有的构造方法，包括public、protected、(默认)、private的
        Constructor[] cons1 = c.getDeclaredConstructors();
        for (Constructor con:cons1){
            System.out.println(con);
        }
    }
}
