package itheima03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class TestStu {
    public static void main(String[] args) {
        //创建一个list集合，用于保存年级信息
        ArrayList<HashMap<Integer,Student>> grade = new ArrayList<>();
        //创建一个集合保存一个班级的学生信息；
        //因为学生的学号和学生信息具有一一对应的关系，所以使用Map集合保存；
        //因为学生的学号唯一，所以使用学号作键，学生对象作值
        HashMap<Integer,Student> class1 = new HashMap<>();
        //创建学生对象，保存到班级中
        Student s1 = new Student(001,"张三",88);
        Student s2 = new Student(002,"李四",66);
        Student s3 = new Student(003,"赵武",77);

        class1.put(s1.getId(),s1);
        class1.put(s2.getId(),s2);
        class1.put(s3.getId(),s3);
        //将这个班级添加到年级集合中
        grade.add(class1);
        //创建几个学生对象，保存到另一个班级中
        HashMap<Integer,Student> class2 = new HashMap<>();
        //创建学生对象，保存到班级中
        Student s4 = new Student(004,"刘能",55);
        Student s5 = new Student(005,"赵四",99);
        Student s6 = new Student(006,"谢大脚",44);

        class2.put(s4.getId(),s4);
        class2.put(s5.getId(),s5);
        class2.put(s6.getId(),s6);
        //将这个班级添加到年级集合中
        grade.add(class2);

        //遍历集合
        int c = 1;
        for(HashMap<Integer, Student> map:grade){


            System.out.println("本年级的"+c+"班级为:");
            for (Integer integer :map.keySet()){

                System.out.println(integer+"="+map.get(integer));

            }

            c++;

        }


    }
}
