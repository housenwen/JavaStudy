package Annotation.com;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class work {
    public static void main(String[] args) throws IllegalAccessException,
            InstantiationException, InvocationTargetException {
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
