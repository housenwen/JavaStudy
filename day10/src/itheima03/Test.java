package itheima03;

import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> list =new ArrayList<>();
        Student s1 = getStudent();
        Student s2 = getStudent();
        Student s3 = getStudent();

        list.add(s1);
        list.add(s2);
        list.add(s3);


        for (int i = 0; i < list.size(); i++) {

            Student stu = list.get(i);

            System.out.println(stu.getName()+"..."+stu.getAge());
        }

    }

    private static Student getStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.next();
        System.out.println("请输入年龄：");
        String age = scanner.next();

        Student stu = new Student(name,age);

        return stu;
    }

    private static Student getList(ArrayList<Student> list) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入姓名：");
        String name = scanner.next();
        System.out.println("请输入年龄：");
        String age = scanner.next();

        Student stu = new Student(name,age);

        return stu;

    }


}
