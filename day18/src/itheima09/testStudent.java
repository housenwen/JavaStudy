package itheima09;


//需求:使用LinkedHashSet集合，保存自定义的Student类对象；要求：
//
//1. Student应该具有姓名、性别、年龄和学号四个属性；
//2. 如果Student对象的学号相同，就认为是同一个人；
import java.util.Collections;
import java.util.LinkedHashSet;

public class testStudent {
    public static void main(String[] args) {
        LinkedHashSet<Student> set = new LinkedHashSet<>();
        Student s1 = new Student("周杰伦","男",21,"00111");
        Student s2 = new Student("王力宏","男",22,"00222");
        Student s3 = new Student("谢霆锋","男",23,"00333");
        Student s4 = new Student("周杰伦","男",24,"00222");
        Student s5 = new Student("王力宏","男",25,"00111");

        Collections.addAll(set,s1,s2,s3,s4,s5);

        for (Student s :set){
            System.out.println(s);
        }


    }
}
