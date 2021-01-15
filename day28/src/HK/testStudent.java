package HK;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class testStudent {
    public static void main(String[] args) throws Exception {
        //使用反射获取Student的Class对象
        Class clazz = Student.class;
        //获取 “公有、全参构造方法”
        Constructor con = clazz.getDeclaredConstructor(String.class,int.class);
        //调用 “公有、全参构造方法”传入：“柳岩”,17两个参数
        Student stu = (Student) con.newInstance("柳岩",17);
        //反射获取show()方法
        Method show = clazz.getDeclaredMethod("show");
        //调用show()方法
        show.invoke(stu);
    }
}
