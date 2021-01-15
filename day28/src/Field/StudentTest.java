package Field;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class StudentTest {
    /**
        Field[] getFields();
            * 获得所有的成员变量对应的Field对象，只能获得public的
        Field[] getDeclaredFields();
            * 获得所有的成员变量对应的Field对象，包含private的
     */
    @Test
    public void test02() throws Exception{
        // 获得Class对象
        Class c = Student.class;
        // 获得所有的成员变量对应的Field对象
        // Field[] fields = c.getFields();
        // 获得所有的成员变量对应的Field对象，包括private
        Field[] fields = c.getDeclaredFields();
        for (Field field:fields){
            System.out.println(field);
        }
    }
    /**
        Field getField(String name);
            根据成员变量名获得对应Field对象，只能获得public修饰
        Field getDeclaredField(String name);
            *  根据成员变量名获得对应Field对象，包含private修饰的
     */
    @Test
    public void test01() throws Exception{
        // 获得Class对象
        Class c = Student.class;
        // 创建对象
        Object obj = c.newInstance();
        Field field = c.getField("name");
        field.set(obj,"jack");

        // 获得指定对象obj成员变量name的值
        System.out.println(field.get(obj));
        // 获得成员变量的名字
        System.out.println(field.getName());

        // 给成员变量gender赋值
        // 获得成员变量gender对应的Field对象
        Field field1 = c.getDeclaredField("gender");
        field1.setAccessible(true);
        field1.set(obj,"男");
        System.out.println(obj);
    }
}