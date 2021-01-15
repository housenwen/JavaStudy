package Field;

import org.junit.Test;

import java.lang.reflect.Field;

public class test {
    @Test
    public void test02() throws Exception{
        Class c = Student.class;
        Student stu  = (Student) c.newInstance();
        Field field = c.getField("name");
        field.set(stu,"jack");
        System.out.println(field.getName());
        System.out.println(field.get(stu));
    }
    @Test
    public void test01() throws Exception{
        Class c = Student.class;
        Student stu = (Student) c.newInstance();
        Field field = c.getDeclaredField("gender");
        field.setAccessible(true);
        field.set(stu,"男");
        System.out.println(field.getName());
        System.out.println(stu);
    }
    @Test
    public void test() throws Exception{
        Class c = Student.class;
        Field[] fields = c.getDeclaredFields();
        for (Field field:fields){
            field.setAccessible(true);
            System.out.println(field);
        }
    }


}
