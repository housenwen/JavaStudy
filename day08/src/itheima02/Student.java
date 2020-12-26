package itheima02;

public class Student {
    String name;
    int age;
    public void study(){
        System.out.println("好好学习");
    }
}
 class TestStudent {
    public static void main(String[] args) {
        Student s = new Student();
        System.out.println();
        System.out.println(s);
        System.out.println(s.name+"..."+s.age);
        s.name = "阿强";
        s.age = 23;
        System.out.println(s.name+"..."+s.age);
        s.study();
        System.out.println(s.name);
    }
 }
