package Fanshe;

import java.lang.reflect.Method;

public class DemoTest1 {
    public static void main(String[] args) throws Exception {
        Class c = Class.forName("Fanshe.Demo");
        Demo demo = (Demo) c.newInstance();
        Object obj = c.newInstance();
        Method method = c.getMethod("show", String.class);
        method.invoke(demo,"java");
        method.invoke(obj,"world");
        Method method1 = c.getMethod("show1");
        method1.invoke(obj);
    }

}