package Fanshe1;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * (1) 写一个Properties格式的配置文件，配置文件内容如下：
 *
 *  className=com.itcast.test07.DemoClass
 *
 * (2)写一个程序，读取这个Properties配置文件，获得类的完整名称并加载这个类，
 *
 * (3)用反射的方式运行run方法。
 */
public class DemoClassTest {
    public static void main(String[] args) throws Exception,ClassNotFoundException{
        // 创建集合对象
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("properties");
        props.load(fis);
        String className = (String) props.get("className");
        System.out.println(className);

        Class c = Class.forName(className);
        Object obj = c.newInstance();
        Method runMethod = c.getMethod("run");
        runMethod.invoke(obj);
        fis.close();

    }
}