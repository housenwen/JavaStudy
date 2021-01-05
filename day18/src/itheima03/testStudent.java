package itheima03;

import java.util.Collections;
import java.util.HashSet;

//       请按以下要求顺序编码：
//l  定义学生类Student，属性：姓名、性别、年龄
//l  定义测试类，及main()方法
//Ø  定义一个存储Student类型的HashSet集合
//Ø  创建以下三个Student对象
//张三,男,20
//李四,女,21
//张三,男,20
//Ø  将上述三个对象存储到Set集合中
//Ø  使用增强for遍历集合，获取每个Student对象，并打印属性值；
//Ø  请实现集合中不能存储姓名年龄相同的元素
public class testStudent {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();
        Student s1 = new Student("張三","男",20);
        Student s2 = new Student("李四","女",21);
        Student s3 = new Student("張三","男",20);

        Collections.addAll(set,s1,s2,s3);

        for (Student stu:set){
            System.out.println(stu);
        }

    }
}
