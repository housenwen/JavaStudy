package Annotation;

public class MyTestClass {

    @MyTest
    public void show1(){
        System.out.println("show1()方法执行");
    }
    @MyTest
    public void show2(){
        System.out.println("show2()方法执行");
    }

    public void show3(){
        System.out.println("show3()方法执行");
    }
}
