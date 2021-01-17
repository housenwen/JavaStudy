package Zhujie;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请定义注解@MyAnno2：
 * 1) 包含一个String类型的属性“type”，并且定义默认值“java”。
 * 2) 此注解只能修饰“字段”。
 * 3) 此注解只需要能够在源码中使用。
 * 4) 定义测试类：Test2，随意定义一个成员属性，并使用此注解；
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface MyAnno2 {
    public String type() default "java";
}
class Test2{
    @MyAnno2
    int num;
    public static void main(String[] args) {

    }
}
