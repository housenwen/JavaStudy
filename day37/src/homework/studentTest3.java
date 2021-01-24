package homework;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class studentTest3 {
    public static void main(String[] args) throws Exception{
        student s = new student("周杰伦",26);
        setProperty(s,"age",20);

        System.out.println(s);

        Object obj = getProperty(s,"name");
        System.out.println(obj);

    }

    private static Object getProperty(student s, String name) throws NoSuchFieldException, IllegalAccessException {
        Class c = student.class;
        Field pNameField = c.getDeclaredField(name);
        pNameField.setAccessible(true);
        return pNameField.get(s);
    }


    public static void setProperty(Object obj,String pName,Object value) throws Exception{
        Class clz = obj.getClass();
        Field pNameFiled= clz.getDeclaredField(pName);
        pNameFiled.setAccessible(true);
        pNameFiled.set(obj,value);
    }


}