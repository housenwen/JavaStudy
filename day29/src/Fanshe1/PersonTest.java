package Fanshe1;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class PersonTest {
    public static void main(String[] args) throws Exception{
        Class c = Person.class;
        Constructor constructor = c.getDeclaredConstructor(int.class, String.class);
        Person person = (Person) constructor.newInstance(30,"灭绝师太");
        System.out.println(person);
        System.out.println("---------------------------------------");
        Method setNameMethod = c.getMethod("setName", String.class);
        setNameMethod.invoke(person,"张三丰");
        Field ageField = c.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(person,50);
        System.out.println(person);
    }

}