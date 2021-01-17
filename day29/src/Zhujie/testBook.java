package Zhujie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

/**
 * 需求：定义一个注解：Book
 * 	* 包含属性：String value() 书名
 * 	* 包含属性：double price() 价格，默认值为 100
 * 	* 包含属性：String[] authors() 多位作者
 * 1. 定义类在成员方法上使用Book注解
 * 2. 解析获得该成员方法上使用注解的属性值。
 */
public class testBook {
    @book(value = "西游记", authors = {"罗贯中", "周杰伦"})
    public void sell() {
    }

    public static void main(String[] args) throws NoSuchMethodException {
        Class c = testBook.class;
        Method m = c.getMethod("sell");
        if (m.isAnnotationPresent(book.class)) {
            book b = m.getAnnotation(book.class);
            System.out.println(b);
        }
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface book {
    String value();

    public double price() default 100;

    String[] authors();
}
