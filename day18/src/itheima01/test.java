package itheima01;

import Sex.Sex;
import com.sun.deploy.util.UpdateCheck;

import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> list = new ArrayList<>();


        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("【学员信息管理】");
            System.out.println("1.添加学员   2.删除学员   3.修改学员   4.查询学员   5.退出");
            System.out.println();
            System.out.println("请输入功能序号：");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    addStudent(list);
                    System.out.println("添加成员");
                    break;
                case 2:
                    deleteStudent(list);
                    System.out.println("删除成员");
                    break;
                case 3:
                    UpdateStudent(list);
                    System.out.println("修改成员");
                    break;
                case 4:
                    selectAllStudent(list);
                    System.out.println("查看所有成员");
                    break;
                case 5:

                    System.out.println("谢谢使用再见！");
                    System.exit(0);
                    break;
                default:
                    System.out.println("输入错误，请重新输入!");
                    break;
            }
        }
    }
//添加成员
    private static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入姓名：");
        String name = sc.next();
        System.out.println("请输入性别：");
        Sex sex = Sex.valueOf(sc.next());
        System.out.println("请输入生日：");
        String birthday = sc.next();
        System.out.println("请输入年龄：");
        int age = sc.nextInt();

        stud stud = new stud(++Utils.stuId, name, sex, birthday, age) ;

         list.add(stud);

        System.out.println("信息添加成功！");


    }
//删除成员
    private static void deleteStudent(ArrayList<Student> list) {
        System.out.println("请删除你要的成员id：");
        Scanner sc  = new Scanner(System.in);
        int id = sc.nextInt();

        for (int i = 0; i < list.size(); i++) {
            Student student = list.get(i);
            if (student.getId()==id){
                System.out.println("查询要删除的成员信息：");
                System.out.println(student);
                System.out.println("【确认】您确定要删除这条信息吗(y)？");
                String str = sc.next();
                if (str.equals("y")){
                    list.remove(i);
                    System.out.println("删除成功！！！！");
                    return;
                }else {
                    System.out.println("【取消】操作被取消！");
                    return;//结束方法
                }

            }
        }
        System.out.println("信息不存在，请重新输入！");
            }



    //修改成员信息
    private static void UpdateStudent(ArrayList<Student> list) {
        System.out.println("请输入要修改的学员ID：");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        for (int i = 0; i < list.size(); i++) {

            Student stu = list.get(i);

            if (stu.getId()==id){
                System.out.println("【查询结果】要修改的学员信息：");
                System.out.println(stu);
                System.out.println("请输入新姓名：");
                String newName = sc.next();
                System.out.println("请输入新性别：");
                Sex newSex = Sex.valueOf(sc.next());
                System.out.println("请输入新出生日期：");
                String newBirthday = sc.next();
                System.out.println("请输入新年龄：");
                int newAge = sc.nextInt();

                stu.setName(newName);
                stu.setSex(newSex);
                stu.setBirthday(newBirthday);
                stu.setAge(newAge);

                System.out.println("【成功】学员信息修改成功！");
                return;

            }

        }
        System.out.println("【错误】学员ID：" + id + " 没找到！");

    }

    //查询所有学生
    private static void selectAllStudent(ArrayList<Student> list) {
        if (list.isEmpty()){
            System.out.println("无数据，请重新输入数据再查询！");
        }else {
//            System.out.println(list);
            for (int i = 0; i < list.size(); i++) {
                Student s = list.get(i);
                System.out.println(s);
            }

        }
    }
}