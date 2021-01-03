package test;

import java.util.ArrayList;
import java.util.Collection;

public class test01 {
    public static void main(String[] args) {
        ArrayList<Student> list =new ArrayList<>();
        Student s1 = new Student("make",98);
        Student s2 = new Student("jin",92);
        Student s3 = new Student("tom",91);

        list.add(s1);
        list.add(s2);
        list.add(s3);

        for(Student s : list){

            System.out.println(s);
        }
        System.out.println(list.size());

        System.out.println(list.isEmpty());

        System.out.println(list.contains(s1));

        System.out.println(list);


    }
}
