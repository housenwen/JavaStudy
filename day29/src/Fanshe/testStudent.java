package Fanshe;

import java.lang.reflect.Constructor;

/**
 * 3.定义一个Student类，用反射去创建一个Student对象,使用两种方式
 * 通过Class对象的方法创建。
 * 通过Constructor对象的方法创建。
 */
public class testStudent {
    public static void main(String[] args) throws Exception{
        Class c = Class.forName("Fanshe.Student");
        Object obj = c.newInstance();
        System.out.println(obj);
        System.out.println("--------------------");
        Constructor con = c.getDeclaredConstructor(String.class,int.class);
        Object obj1 = con.newInstance("jack",23);
        System.out.println(obj1);
    }
}
