package Annotation;

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
@Book(value ="水浒传",price = 50,author = {"施耐庵","刘德华"})
public class BookStore{
    @Book(value = "西游记",author = {"吴承恩"})
    String str1;
    @Book(value = "西游记",author = {"吴承恩"})
    public BookStore(String str1){
        this.str1 = str1;
    }
    @Book(value = "西游记",author = {"吴承恩"})
    public void sell(){

    }
}
