package itheima01;

import java.util.ArrayList;

//需求：定义一个方法，方法接收一个集合对象（泛型为Student），方法内部将年龄低于18的学生对象找出
//并存入新集合对象，方法返回新集合。
//思路：
//① 定义方法，方法的形参定义为ArrayList<Student> list
//② 方法内部定义新集合，准备存储筛选出的学生对象 ArrayList<Student> newList
//③ 遍历原集合，获取每一个学生对象
//④ 通过学生对象调用getAge方法获取年龄，并判断年龄是否低于18
//⑤ 将年龄低于18的学生对象存入新集合
//⑥ 返回新集合
//⑦ main方法中测试该方法
public class Test {
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        Student s1 = new Student("张三1", 11);
        Student s2 = new Student("张三2", 12);
        Student s3 = new Student("张三3", 23);
        Student s4 = new Student("张三4", 14);
        Student s5 = new Student("张三5", 25);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        ArrayList<Student> newList = getList(list);
        for (int i = 0; i < newList.size(); i++) {
            Student temp = list.get(i);

            System.out.println(temp.getName() + "..." + temp.getAge());
        }

    }

    public static ArrayList<Student> getList(ArrayList<Student> list) {

        ArrayList<Student> newList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
//            int age = s.getAge();
            if (s.getAge() < 18) {
                newList.add(s);
//                i--;
            }
        }
        return newList;
    }
}
