package itheima05;
//需求：定义一个方法，方法接收一个集合对象（泛型为Student），方法内部将年龄低于18的学生对象找出
//并存入新集合对象，方法返回新集合。
import java.util.ArrayList;

public class test {
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();

        Student s1 = new Student("张1",11);
        Student s2 = new Student("张2",12);
        Student s3 = new Student("张3",13);
        Student s4 = new Student("张4",24);
        Student s5 = new Student("张5",15);

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);

        getList(list);

        for (int i = 0; i < list.size(); i++) {
            Student s = list.get(i);
            System.out.println(s.getName()+"..."+s.getAge());
        }

    }

    public static void getList(ArrayList<Student> list) {

        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            int age = student.getAge();
            if (age>18){
                list.remove(i);
                i--;
            }
        }
    }
}
