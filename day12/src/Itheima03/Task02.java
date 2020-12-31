package Itheima03;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * 需求说明：
 * 		从控制台接收3名学员的信息，
 * 		每条进行存储到一个Student对象中，将多个Student对象存储到一个集合中。
 * 		输入完毕后，将所有学员信息存储到文件Student.txt中。
 * 		每名学员信息存储一行，多个属性值中间用逗号隔开。
 */
public class Task02 {
    public static void main(String[] args) throws IOException {
        //1.定义学生类, 定义存学生的集合
        ArrayList<Student> list = new ArrayList<Student>();
        //2.通过3次循环，完成如下操作
        Scanner sc = new Scanner(System.in);
        for (int i = 1; i <= 3; i++) {
            //键盘输入学生的信息，
            System.out.print("请输入第"+i+"名学生的学号：");
            String id = sc.next();
            System.out.print("请输入第"+i+"名学生的姓名：");
            String name = sc.next();
            System.out.print("请输入第"+i+"名学生的性别：");
            String sex = sc.next();
            System.out.print("请输入第"+i+"名学生的年龄：");
            String age = sc.next();
            //把信息封装到Student对象中
            Student s = new Student(id, name, sex, age);
            //把Student对象存到集合里
            list.add(s);
        }
        //3.将所有学员信息存储到文件Student.txt中。
        BufferedWriter out = new BufferedWriter(new FileWriter("Student.txt"));
        //每名学员信息存储一行，多个属性值中间用逗号隔开。
        for (int i = 0; i < list.size(); i++) {
            //1.获取集合中每一个学生对象
            Student s = list.get(i);
            //2.获取对象中的每一个属性值,多个属性值中间用逗号隔开
            String line = s.getId() +","+ s.getName() +","+ s.getSex() +","+ s.getAge();
            //3.按照指定的格式把对象的属性值，写入到文件中
            out.write(line);
            out.newLine();
            out.flush();
        }
        out.close();//关闭流

    }
}
