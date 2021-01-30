package CW;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 21、键盘录入3个学生信息(姓名,语文成绩,数学成绩,英语成绩)。
 * 要求按照成绩总分从高到低写入文本文件，最后在从文件中把读取数据显示在控制台
 * 格式：姓名,语文成绩,数学成绩,英语成绩 举例：林青霞,98,99,100
 */
public class test1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Scanner sc = new Scanner(System.in);
        List<Student> list = new ArrayList<>();

        System.out.println("请键盘录入3个学生信息(姓名,语文成绩,数学成绩,英语成绩");
        for (int i = 0; i < 3; i++) {

            System.out.println("请输入姓名：");
            String name = sc.next();
            System.out.println("请输入语文成绩");
            int chinese = sc.nextInt();
            System.out.println("请输入数学成绩");
            int math = sc.nextInt();
            System.out.println("请输入英语成绩");
            int english = sc.nextInt();
            list.add(new Student(name,chinese,math,english));

        }

        //用Lambda进行排序
        Collections.sort(list,(Student s2, Student s1) ->{
            return s1.getEnglish()+s1.getMath()+s1.getChinese() -
                    s2.getEnglish()+s2.getMath()+s2.getChinese();
        });

        for (Student student:list){
            System.out.println(student);
        }

        //序列化操作
        //把list写入到student文本中。序列化
        // 创建 序列化流
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("student.txt"));
        // 写出对象
        oos.writeObject(list);
        // 释放资源
        oos.close();

        // 反序列化
        ObjectInputStream ois  = new ObjectInputStream(
                new FileInputStream("student.txt"));
        // 读取对象,强转为ArrayList类型
        ArrayList<Student> stuList  = (ArrayList<Student>)ois.readObject();


        System.out.println("数据写入文件完毕");
        System.out.println("文件中的数据展示如下");
        System.out.println("姓名"+","+"语文成绩"+","+"数学成绩"+","+"英语成绩");
        for (int i = 0; i < stuList.size(); i++ ){
            Student s = stuList.get(i);
            System.out.println(s.getName()+","+ s.getChinese()+","+
                    s.getMath()+","+s.getEnglish());
        }

    }
}
