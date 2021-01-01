package itheima;

public class JavaTeacher extends YuanGong {

    public JavaTeacher() {
    }

    public JavaTeacher(String name, String sex, String age) {
        super(name, sex, age);
    }

    @Override
    public void work() {
        System.out.println("疯狂授课！");
    }
}
