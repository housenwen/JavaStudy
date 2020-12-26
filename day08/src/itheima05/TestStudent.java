package itheima05;

public class TestStudent {
    public static void main(String[] args) {
//        无参数构造方法创建对象，通过setXxx给成员变量进行赋值
        Student stu1 = new Student();
        stu1.setName("张三");
        stu1.setAge(23);
        stu1.show();
//        通过带参数的构造方法直接给属性赋值
        Student stu2 = new Student("李四",24);
        stu2.show();

    }
}
