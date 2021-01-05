package itheima06;

import java.util.ArrayList;
import java.util.Collections;

//需求：定义一个学生类Student，包含三个属性姓名、年龄、性别，私有成员变量,生成无参，有参构造方法，生成get/set方法并重写toString()方法。
// 创建三个学生对象存入ArrayList集合中。
//
//1. 遍历集合遍历输出。（输出格式如：姓名：张三, 年龄：23, 性别：男）
//2. 求出年龄最大的学生，然后将该对象的姓名变为：小猪佩奇。
public class test {
    public static void main(String[] args) {
        ArrayList<Student> list = new ArrayList<>();
        Student s1 = new Student("張三",23,"男");
        Student s2 = new Student("李四",21,"女");
        Student s3 = new Student("王五",25,"男");
        Collections.addAll(list,s1,s2,s3);

        for (Student student:list){
            System.out.println("姓名："+student.getName()+", 年龄："+student.getAge()+", 性别："+student.getSex());
        }

        change(list);


    }

    private static void change(ArrayList<Student> list) {

        int max = 0;
        int num = 0;
        for (int i = 0; i < list.size(); i++) {
            if (max<list.get(i).getAge()){
                max = list.get(i).getAge();
                num =i;
            }
        }
        System.out.println(max);

//        for (int i = 0; i < list.size(); i++) {
//
//            if (list.get(i).getAge()==max){
//                num=i;
//            }
//        }
        list.get(num).setName("小豬佩奇");

        System.out.println(list);
    }
}
