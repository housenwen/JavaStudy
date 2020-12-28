package itheima;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {
//        1.创建集合对象
        ArrayList<Student> list = new ArrayList<>();
//        2.创建学生对象，并赋值
        Student student1 = new Student("张三","23");
        Student student2 = new Student("李四","24");
        Student student3 = new Student("张三","25");
//        3.添加到学生对象到集合中
        list.add(student1);
        list.add(student2);
        list.add(student3);
//        4.遍历集合，采用通用遍历方式
//        System.out.println(list);

        for (int i = 0; i < list.size(); i++) {

            Student temp = list.get(i);
            System.out.println(temp.getName()+"..."+temp.getAge());

        }

    }
}
