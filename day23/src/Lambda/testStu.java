package Lambda;

import java.lang.reflect.Array;
import java.util.Arrays;

public class testStu {
    public static void main(String[] args) {

        Student[] students = new Student[6];
        for (int i = 0; i < students.length; i++) {
            students[i]=new Student(i+"号",100-i);
        }
        System.out.println(Arrays.toString(students));

        Arrays.sort(students,(o1, o2) -> o1.getNum()-o2.getNum());

        for (Student s:students){
            System.out.println(s);
        }

    }
}
