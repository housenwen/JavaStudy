package Annotation;

import Annotation.com.MyTest;
import Annotation.com.MyTestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {
    public static void main(String[] args) throws IllegalAccessException,
            InstantiationException, InvocationTargetException {
        //1.先获取MyTestClass的字节码对象
        Class<Annotation.com.MyTestClass> cls = MyTestClass.class;

        //2.获取所有的方法，遍历方法对象
        Method[] methods = cls.getMethods();
        Annotation.com.MyTestClass obj = cls.newInstance();
        //3.判断方法是否被MyTest注解，如果是直接运行起来
        for (Method method:methods){
            if (method.isAnnotationPresent(MyTest.class)){
                method.invoke(obj);
            }
        }

    }
}
