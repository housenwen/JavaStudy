package Annotation;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;

public class testAnnotation {
    public static void main(String[] args) throws NoSuchMethodException{

        //解析BookStores类型上的Book注解
        //1.先获取BookStore的Class对象
        Class<BookStore> cls = BookStore.class;

        //2.判断类型上是否存在Book注解
        if (cls.isAnnotationPresent(Book.class)){
            //3.如果存在就解析获取Book注解对象
            Book book = cls.getAnnotation(Book.class);

            System.out.println("类型上：book.name="+book.name());
            System.out.println("类型上：book.price="+book.price());
            System.out.println("类型上：book.authors="
                    + Arrays.toString(book.authors()));
        }
        Method method = cls.getMethod("test");
        //3.如果存在就进行解析
        if (method.isAnnotationPresent(Book.class)){
            Book book = method.getAnnotation(Book.class);
            System.out.println("方法上：book.name="+book.name());
            System.out.println("方法上：book.price="+book.price());
            System.out.println("方法上：book.authors="
                    +Arrays.toString(book.authors()));

        }

    }
    //1. 定义注解Book，要求如下：
//   - 包含属性：String value()   书名
//   - 包含属性：double price()  价格，默认值为 100
//   - 包含属性：String[] authors() 多位作者
//   - 限制注解使用的位置：类和成员方法上 【Target】
//   - 指定注解的有效范围：RUNTIME  【Retention】
    @Target({ElementType.TYPE,ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Book{
        String name();//书名
        double price() default 100;
        String[] authors();

    }
    //2. 定义BookStore类，在类和成员方法上使用Book注解
    @Book(name="西游记",authors = "吴承恩")
            //数组赋值如果只有一个值，可以不用大括号
    class BookStore{
        //@Book(name="红楼梦",price = 100,authors = {"曹雪芹","高鹗"})
        String book;//字段位置
        @Book(name="红楼梦",price = 100,authors = {"曹雪芹","高鹗"})
        public void test(){

        }
    }
}
