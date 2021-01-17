package Zhujie;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请定义注解@MyAnno3：
 * 1) 包含一个String类型的属性“type”，不定义默认值。
 * 2) 包含一个int[]数组类型的属性“intArr”，不定义默认值。
 * 3) 此注解只能修饰“方法”。
 * 4) 此注解要出现在源码和字节码中。
 * 5) 定义测试类：Test3，随意定义一个成员方法，并使用此注解；
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface MyAnno3 {
    public String type();
    public int[] intArr();
}
class test3{
    @MyAnno3(type = "java",intArr = {1,2,34,5})
    public void show(){

    }
}
