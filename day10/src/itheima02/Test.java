package itheima02;

import java.util.ArrayList;
import java.util.Scanner;

//创建学生管理系统
public class Test {
    public static void main(String[] args) {
//        键盘录入
        Scanner sc = new Scanner(System.in);
//        创建集合容器对象（学生类集合）
        ArrayList<Student> list = new ArrayList<>();

        lo:
        while (true) {
//            搭建主菜单：
            System.out.println("--------欢迎来到学生管理系统---------");
            System.out.println("1.添加学生");
            System.out.println("2.删除学生");
            System.out.println("3.修改学生");
            System.out.println("4.查看学生");
            System.out.println("5.退出");
            System.out.println("请输入您的选择：");

            String choice = sc.next();

            switch (choice) {
                case "1":
//                    System.out.println("添加学生");
                    addStudent(list);
                    break;
                case "2":
//                    System.out.println("删除学生");
                    deleteStudent(list);

                    break;
                case "3":
//                    System.out.println("修改学生");
                    updateStudent(list);

                    break;
                case "4":
//                    System.out.println("查看学生");
                    queryStudent(list);
                    break;
                case "5":
                    System.out.println("感谢您的使用！下次再见！");
                    break lo;
                default:
                    System.out.println("您输入有误！");
                    break;

            }


        }

    }
//       查询学生方法
    private static void updateStudent(ArrayList<Student> list) {
        System.out.println("请输入您要查询的学号：");
        Scanner sc = new Scanner(System.in);

        String updateSid = sc.next();

        int index = getIndex(list,updateSid);

        if (index == -1){
            System.out.println("您输入的学号不存在，请重新输入");
        }else {
            System.out.println("请输入新的姓名：");
            String name = sc.next();
            System.out.println("请输入新的年龄：");
            int age = sc.nextInt();
            System.out.println("请输入新的生日：");
            String birthday = sc.next();
            Student stu = new Student(updateSid,name,age,birthday);
            list.set(index,stu);
            System.out.println("修改成功！");
        }

    }

    //    删除学生方法
    private static void deleteStudent(ArrayList<Student> list) {
        System.out.println("请输入您要查询的学号：");
        Scanner sc = new Scanner(System.in);

        String deleteSid = sc.next();

        int index = getIndex(list,deleteSid);

        if (index == -1){
            System.out.println("您输入的学号不存在，请重新输入");
        }else {

            list.remove(index);

            System.out.println("删除成功！");

        }
    }

    //    查看学生方法
    public static void queryStudent(ArrayList<Student> list) {

        if (list.size() == 0) {
            System.out.println("无信息，请添加后查询");
            return;
        }
        System.out.println("学号\t姓名\t年龄\t生日");

        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getSid() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getBirthday());
        }

    }

    //  添加学生方法
    public static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);

        String sid;

        while (true){
            System.out.println("请输入学号：");
             sid = sc.next();
            int index = getIndex(list, sid);
            if (index==-1){
//                学号sid不存在，可以储存
                break;
            }else {
                System.out.println("您输入的学号已存在,请重新录入！");
            }

        }

        System.out.println("请输入姓名：");
        String name = sc.next();
        System.out.println("请输入年龄：");
        int age = sc.nextInt();
        System.out.println("请输入生日：");
        String birthday = sc.next();

        Student stu = new Student(sid, name, age, birthday);

        list.add(stu);

        System.out.println("添加成功");

    }

    // 修改删除：getIndex接受一个集合对象和一个学生学号
//    查找这个学号在索引出现的位置
    public static int getIndex(ArrayList<Student> list, String sid) {
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String id = stu.getSid();
            if (id.equals(sid)) {

                index = i;
                break;
                
            }
        }

        return index;
    }
}

