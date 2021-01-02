package itheima02;

import java.util.ArrayList;
import java.util.Scanner;

public class test01 {
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("--------欢迎来到学生管理系统--------");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看所有学生");
            System.out.println("5 退出");

            System.out.println("请选择按钮1-5：");

            int num = sc.nextInt();

            switch (num) {
                case 1:
                    System.out.println("添加学生");
                    addStudent(list);
                    break;
                case 2:
                    System.out.println("删除学生");
                    delete(list);
                    break;
                case 3:
                    System.out.println("修改学生");
                    revise(list);
                    break;
                case 4:
                    System.out.println("查看所有学生");
                    lookStudent(list);

                    break;
                case 5:
                    System.out.println("退出");
                    System.exit(0);
                    break;
                default:
                    System.out.println("您输入错误，请重新输入：");
                    break;

            }

        }

    }
//    修改学生方法
    private static void revise(ArrayList<Student> list) {

        System.out.println("请输入您需要修改的学号：");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        int index = getIndex(list,str);

        if (index==-1){
            System.out.println("该信息不存在，请重新录入！");
        }else {
            System.out.println("请输入新姓名：");
                String name = sc.next();
                System.out.println("请输入新年龄：");
                int age = sc.nextInt();
                System.out.println("请输入新生日：");
                String birthday = sc.next();

                Student s2 = new Student(str,name,age,birthday);

                list.set(index,s2);

                System.out.println("修改成功！");
        }

//        for (int i = 0; i < list.size(); i++) {
//
//            if (str.equals(list.get(i).getId())){
//
//                System.out.println("请输入新姓名：");
//                String name = sc.next();
//                System.out.println("请输入新年龄：");
//                int age = sc.nextInt();
//                System.out.println("请输入新生日：");
//                String birthday = sc.next();
//
//                Student s2 = new Student(str,name,age,birthday);
//
//                list.set(i,s2);
//
//                System.out.println("修改成功！");
//
//            }else {
//                System.out.println("该信息不存在，请重新录入！");
//            }
//        }

    }
//    删除学生方法
    private static void delete(ArrayList<Student> list) {

        System.out.println("请输入您需要删除的学号：");
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        int index =  getIndex(list,str);

        if (index==-1){
            System.out.println("该信息不存在，请重新录入！");
        }
        else {
            list.remove(index);
            System.out.println("删除成功！");
        }

//        for (int i = 0; i < list.size(); i++) {
//            if (str.equals(list.get(i).getId())){
//                list.remove(i);
//                System.out.println("删除成功！");
//            }else {
//                System.out.println("该信息不存在，请重新录入！");
//            }
//        }

    }
//    查看学生方法
    private static void lookStudent(ArrayList<Student> list) {

        if (list.size()==0){
            System.out.println("无学生信息，请录入！");
            return;
        }


        System.out.println("学号："+"\t"+"姓名:"+"\t"+"年龄:"+"\t"+"生日:");

        for (int i = 0; i < list.size(); i++) {
            Student s =  list.get(i);

            System.out.println(s.getId()+"\t"+s.getName()+"\t"
                    +s.getAge()+"岁\t"+s.getBirthday());

        }

    }
//    添加学生方法
    private static void addStudent(ArrayList<Student> list) {

        Scanner sc = new Scanner(System.in);

        String id;

        while (true){
            System.out.println("请输入学号：");
            id = sc.next();
            int index = getIndex(list,id);
            if (index==-1){
                break;
            }else {
                System.out.println("学号存在！！！重新输入：");
            }
        }


        System.out.println("请输入姓名：");
        String name = sc.next();
        System.out.println("请输入年龄：");
        int age = sc.nextInt();
        System.out.println("请输入生日：");
        String birthday = sc.next();

        Student s = new Student(id,name,age,birthday);

        list.add(s);

        System.out.println("添加学生成功");
    }
//    查看学生在集合中的索引位：
    public static int getIndex(ArrayList<Student> list,String id){

        int index = -1;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("输入您要删");

        for (int i = 0; i < list.size(); i++) {

            Student stu = list.get(i);

            String idstr = stu.getId();


            if (idstr.equals(id)){


                index = i;
            }

        }


        return index;
    }
}


