package Classloader;

public class work2 {
    public static void main(String[] args) throws Exception {

        // 获得Class对象
        Class c = Student.class;
        // 获得类名字符串：类名
        System.out.println(c.getSimpleName());
        // 获得类全名：包名+类名
        System.out.println(c.getName());
        // 创建对象
        Student stu = (Student) c.newInstance();
        System.out.println(stu);
        // 创建对象
        Student student = new Student();
        System.out.println(student);

    }
}
