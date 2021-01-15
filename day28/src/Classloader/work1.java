package Classloader;

public class work1 {
    public static void main(String[] args) throws ClassNotFoundException{

        // 获得Student类对应的Class对象
        Class c1 = Student.class;
        System.out.println(c1);

        // 创建学生对象
        Student stu = new Student();
        // 通过getClass方法
        Class c2 = stu.getClass();
        System.out.println(c1==c2);

        // 通过Class类的静态方法获得： static Class forName("类全名")
        Class c3 = Class.forName("Classloader.Student");
        System.out.println(c1==c3);
        System.out.println(c2==c3);

    }
}
