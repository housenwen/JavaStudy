package itheima01;

import itheima.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class Test01 {
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        Student s1 = getStudent();
        Student s2 = getStudent();
        Student s3 = getStudent();

        list.add(s1);
        list.add(s2);
        list.add(s3);

        for (int i = 0; i < list.size(); i++) {
            Student temp = list.get(i);
            System.out.println(temp.getName()+"..."+temp.getAge());
        }

    }

    public static Student getStudent(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入姓名：");
        String name = scanner.next();
        System.out.println("请输入年龄：");
        String age = scanner.next();

        itheima.Student s = new Student(name,age);

        return s;

    }
}
