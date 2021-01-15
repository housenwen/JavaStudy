package Interface;

public class work {

    @A("Hello")
    String str1;

    @B("Hello")
    String str2;

    @B(value = "Hello",name = "李四")
    String str3;

}
@interface A{
    String value();
}
@interface B{
    String value();
    String name() default "张三";
}
