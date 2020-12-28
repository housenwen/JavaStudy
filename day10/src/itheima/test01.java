package itheima;
//需求：创建一个存储学生对象的集合，存储3个学生对象，使用程序实现在控制台遍历该集合
//思路：
//① 定义学生类
//② 创建集合对象
//③ 创建学生对象
//④ 添加学生对象到集合中
//⑤ 遍历集合，采用通用遍历格式实现

import java.util.ArrayList;
import java.util.Scanner;

public class test01 {
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

    private static Student getStudent() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入姓名：");
        String name = scanner.next();
        System.out.println("请输入年龄：");
        String age = scanner.next();

        Student s = new Student(name,age);

        return s;
    }

}
