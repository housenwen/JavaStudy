package Method;

import org.junit.Test;

import java.lang.reflect.Method;

public class testStudent {
    // 反射操作静态方法
    @Test
    public void test04() throws Exception{
        // 获得Class对象
        Class c = Student.class;
        // 根据方法名获得对应的公有成员方法对象
        Method method = c.getDeclaredMethod("eat", String.class);
        // 通过method执行对应的方法
        // 暴力反射
        method.setAccessible(true);
        method.invoke(c.newInstance(),"ABC");
    }
    /**
     * Method[] getMethods();
     * 获得类中的所有成员方法对象，返回数组，只能获得public修饰的且包含父类的
     * Method[] getDeclaredMethods();
     * 获得类中的所有成员方法对象，返回数组,只获得本类的，包含private修饰的
     */
    @Test
    public void test03() throws Exception{
        // 获得Class对象
        Class c = Student.class;
        // 获得类中的所有成员方法对象，返回数组，只能获得public修饰的且包含父类的
        // Method[] methods = c.getMethods();
        // 获得类中的所有成员方法对象，返回数组,只获得本类的，包含private修饰的
        Method[] methods = c.getDeclaredMethods();
        for (Method m :methods){
            m.setAccessible(true);
            System.out.println(m);
        }
    }
    /**
      Method getDeclaredMethod(String name,Class...args);
          * 根据方法名和参数类型获得对应的构造方法对象，
   */
    @Test
    public void test02() throws Exception{
        // 获得Class对象
        Class c = Student.class;

        // 根据Class对象创建学生对象
        Student stu = (Student) c.newInstance();
        // 获得sleep方法对应的Method对象
        Method m =  c.getDeclaredMethod("sleep");
        // 暴力反射
        m.setAccessible(true);

        // 通过m对象执行stuy方法
        m.invoke(stu);
    }

    /**
       Method getMethod(String name,Class...args);
           * 根据方法名和参数类型获得对应的构造方法对象，
    */
    @Test
    public void test01() throws Exception {
        // 获得Class对象
        Class c = Student.class;

        // 根据Class对象创建学生对象
        Student stu = (Student) c.newInstance();

        /// 获得study方法对应的Method对象
        Method m2  = c.getMethod("study", int.class);
        // 通过m2对象执行study方法
        m2.invoke(stu,8);
    }

}
