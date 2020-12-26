package itheima;

public class Student {
    String name;
    int age;
    public void  study(){
        System.out.println("好好学习");
    }
}
class TestStudent{
    public static void main(String[] args) {
        Student s1 = new Student();
        System.out.println(s1);
        s1.name = "阿强";
        s1.age = 23;
        System.out.println(s1.name+"..."+s1.age);
        s1.study();
        Student s2 = new Student();
        System.out.println(s2);
        s2.name = "阿珍";
        s2.age = 24;
        System.out.println(s2.name+"..."+s2.age);
        s2.study();
    }
}
