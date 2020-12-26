package itheima03;

public class Student {
    String name;
    int age;
    public void study(){
        System.out.println("好好学习");
    }
}
class TestStudent{
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.name = "阿强";
        Student student = new Student();
        student = s1;
        System.out.println(student.name);

        Student s2 = s1;
        s2.name = "阿珍";
//        student.name = "阿明";

        System.out.println(s1.name+"..."+student.name);
        s1= null;
        System.out.println(s1.name);
        System.out.println(s2.name);
        s2 = null;


    }
}
