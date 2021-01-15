package Interface;

@interface Book {
    String value();
    double price() default 100;
    String[] author();
}
/**
    把注解Book定义到下面的类中：
    当使用注解是，格式： @注解名(属性=值)

    有多个属性，属性要全部赋值，以逗号分隔。如果有默认值可以不用赋值，如果是数组赋值，需要使用大括号
 */
