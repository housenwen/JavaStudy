package TestAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

public class Test {
    public static void main(String[] args) throws Exception {
        //1.先获取MyTestClass的字节码对象
        Class<MyTestClass> cls = MyTestClass.class;

        //2.获取所有的方法，遍历方法对象
        Method[] methods = cls.getMethods();
        //3.判断方法是否被MyTest注解，如果是直接运行起来
        MyTestClass obj = cls.newInstance();
        for (Method method : methods) {
            //System.out.println("method = " + method);
            if (method.isAnnotationPresent(MyTest.class)) {
                method.invoke(obj);
            }
        }
    }
}
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MyTest {

}
class MyTestClass {
    @MyTest
    public void show1() {
        System.out.println("show 1方法执行");
    }


    @MyTest
    public void show2() {
        System.out.println("show 2方法执行");
    }


    public void show3() {
        System.out.println("show 3方法执行");
    }


}
