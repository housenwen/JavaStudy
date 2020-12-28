package itheima06;
//3题、
//
//定义个学生类Student，包含空参构造、满参构造和以下成员变量：
//
//姓名name(String类型)  成绩 score(ing 类型)
//
//生成以上成员变量的set、get方法
//
//定义测试类，创建三个对象，存入集合中，之后遍历集合list，求出三个学生的平均成绩

import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        Student s1 = new Student("张三",80);
        Student s2 = new Student("李四",90);
        Student s3 = new Student("王五",100);

        list.add(s1);
        list.add(s2);
        list.add(s3);

        int sum = 0;

        for (int i = 0; i < list.size(); i++) {

            Student s = list.get(i);

            int score = s.getScore();

            sum += score;
        }
        int ping = sum/3;

        System.out.println("三人的平均分是："+ping);

    }
}
