package itheima01;

import java.util.ArrayList;
////需求：定义一个方法，方法接收一个集合对象（泛型为Student），方法内部将年龄低于18的学生对象找出
////并存入新集合对象，方法返回新集合。
public class test02 {
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        Student s1 = new Student("张三1", 11);
        Student s2 = new Student("张三2", 12);
        Student s3 = new Student("张三3", 23);
        Student s4 = new Student("张三4", 14);
        Student s5 = new Student("张三5", 25);
        Student s6 = new Student("张三6", 26);
        Student s7 = new Student("张三7", 17);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);

       ArrayList<Student> newList =  getList(list);

        for (int i = 0; i < newList.size(); i++) {

            Student s = newList.get(i);
            System.out.println(s.getName() + "..." + s.getAge());

        }

    }

    public static ArrayList getList(ArrayList<Student> list){

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            int age = s.getAge();
            if(age>18){
                list.remove(s);
                i--;
            }
        }
        return list;
    }
}
