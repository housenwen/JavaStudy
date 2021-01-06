package itheima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class testStudent {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<Student>();

        list.add(new Student("rose", 18));
        list.add(new Student("jack", 16));
        list.add(new Student("abc", 20));

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        for (Student student : list) {
            System.out.println(student);
        }
    }
}
