package Fanshe;

import java.lang.reflect.Field;

/**
 *   1.写一个方法，此方法可将obj对象中名为propertyName的属性的值设置为
 *
 *value. public void setProperty(Object obj, String propertyName, Object value){}
 *
 * 1. 写一个方法，此方法可以获取obj对象中名为propertyName的属性的值
 *
 * public Object getProperty(Object obj, String propertyName){}
 */
public class StudentTest {
    public static void main(String[] args) throws Exception{
        Student student = new Student("张三",29);
        setProperty(student,"age",25);
        System.out.println(student);
        Object obj = getProperty(student,"name");
        System.out.println(obj);

    }

    private static Object getProperty(Object obj, String propertyName) throws Exception{
        Class c = obj.getClass();
        Field propertyNameField = c.getDeclaredField(propertyName);
        propertyNameField.setAccessible(true);
        return propertyNameField.get(obj);
    }

    private static void setProperty(Object obj,
                                    String propertyName, Object value) throws Exception {
        Class c = obj.getClass();
        Field propertyNameField = c.getDeclaredField(propertyName);
        //3.设置成可访问的
        propertyNameField.setAccessible(true);
        propertyNameField.set(obj,value);

    }
}
