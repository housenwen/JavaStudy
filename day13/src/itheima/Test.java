package itheima;

public class Test {

    public static void main(String[] args) {

        UITeacher uiTeacher = new UITeacher("张三","男","30");

        uiTeacher.paint();
        uiTeacher.work();

        JavaTeacher javaTeacher = new JavaTeacher();

        javaTeacher.work();




    }

}
