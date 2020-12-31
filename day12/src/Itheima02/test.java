package Itheima02;

public class test {
    public static void main(String[] args) {
        // 调用子类有参数构造器
        Student s2 = new Student("张三",20,99);
        System.out.println(s2.getScore()); // 99
        System.out.println(s2.getName()); // 输出 张三
        System.out.println(s2.getAge()); // 输出 20
        System.out.println("-------------");
//        Student student = new Student("李四",19,219);
        Student s3 = new Student();
        s3.setName("王二麻子");
        s3.setAge(18);
        System.out.println(s3.getScore()); //
        System.out.println(s3.getName()); //
        System.out.println(s3.getAge()); //

    }
}
