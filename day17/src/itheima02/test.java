package itheima02;

import java.util.ArrayList;
import java.util.Scanner;

//一 编程题【学员教师综合管理系统】
//1.1 请编程实现：系统总菜单的循环显示
//1.2 请编程实现：学员信息管理的二级菜单的循环显示
//1.3 请编程实现：学员信息管理的”添加功能”
//1.4 请编程实现：学员信息管理的”查询所有学员”功能
//1.5 请编程实现：学员信息管理的“删除功能”；
//1.6 请编程实现：学员信息管理的“修改功能”；
//1.7 请编程实现：“教师信息管理模块”
public class test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Student> stuList = new ArrayList<>();
        ArrayList<Teacher> teaList = new ArrayList<>();

        while (true){
            System.out.println("1.學生信息管理  2.教師系統管理  3.退出");
            int  op = sc.nextInt();

            switch (op){
                case 1:
                    studentManage(stuList,sc);
                    break;
                case 2:
                    System.out.println("教師管理系統");
                    break;
                case 3:
                    System.out.println("謝謝使用，再見！！");
                    System.exit(0);
                default:
                    System.out.println("您輸入有誤，請重新輸入！");
                    break;

            }
        }

    }
    //学员信息管理
    private static void studentManage(ArrayList<Student> stuList, Scanner sc) {
        //二级菜单
        while (true){
            System.out.println("------------------------------------------------------");
            System.out.println("【学员信息管理】");
            System.out.println("1.添加学员   2.修改学员   3.删除学员   4.查询学员   5.返回");
            System.out.println();
            System.out.println("请输入功能序号：");

            int op = sc.nextInt();

            switch (op){
                case 1:
                    addStudent(stuList, sc);
                    break;
                case 2:
                    updateStudent(stuList, sc);
                    break;
                case 3:
                    deleteStudent(stuList, sc);
                    break;
                case 4:
                    selectAll(stuList, sc);
                    break;
                case 5:
                    return;//结束方法
                default:
                    System.out.println("你的输入有误，请重新输入！");
                    break;
            }

        }
    }
    //查询所有学员
    private static void selectAll(ArrayList<Student> stuList, Scanner sc) {
        System.out.println("【查询结果】");
        if (stuList.size() == 0) {
            System.out.println("无数据");
            return;
        }
        Utils.printPersonList(stuList);//调用工具类打印
    }
    //删除学员
    private static void deleteStudent(ArrayList<Student> stuList, Scanner sc) {
        System.out.println("请输入要删除的学员ID：");
        int stuId = sc.nextInt();
        //查询
        for (int i = 0; i < stuList.size(); i++) {
            Student stu = stuList.get(i);
            if (stu.getId() == stuId) {
                System.out.println("【查询结果】要删除的学员信息：");
                Utils.printPerson(stu);
                System.out.println("【确认】您确定要删除这条信息吗(y/n)？");
                String str = sc.next();
                if ("y".equals(str)) {
                    stuList.remove(i);
                    System.out.println("【成功】数据已被删除！");
                    return;//结束方法
                }else{
                    System.out.println("【取消】操作被取消！");
                    return;
                }

            }

        }
        System.out.println("【错误】学员ID：" + stuId + " 未找到！");
    }
    //修改学员
    private static void updateStudent(ArrayList<Student> stuList, Scanner sc) {
        System.out.println("请输入要修改的学员ID：");
        int stuId = sc.nextInt();
        for (int i = 0; i < stuList.size(); i++) {
            Student stu = stuList.get(i);
            if (stu.getId() == stuId) {
                System.out.println("【查询结果】要修改的学员信息：");
                //打印
                Utils.printPerson(stu);
                //执行修改
                System.out.println("请输入新姓名(保留原值输入0)：");
                String newName = sc.next();
                System.out.println("请输入新性别(保留原值输入0)：");
                String newSex = sc.next();
                System.out.println("请输入新出生日期(yyyy-MM-dd)(保留原值输入0)：");
                String newBirthday = sc.next();

                if (!"0".equals(newName)) {
                    stu.setName(newName);
                }
                if (!"0".equals(newSex)) {
                    stu.setSex(newSex);
                }
                if (!"0".equals(newBirthday)) {
                    stu.setBirthday(newBirthday);
                }
                System.out.println("【成功】学员信息修改成功！");
                return;
            }

        }
        System.out.println("【错误】学员ID：" + stuId + " 没找到！");

    }


    //添加学员
    private static void addStudent(ArrayList<Student> stuList, Scanner sc) {

        System.out.println("请输入学员姓名：");
        String name = sc.next();
        System.out.println("请输入性别：");
        String sex = sc.next();
        System.out.println("请输入出生日期(yyyy-MM-dd)：");
        String birthday = sc.next();

        stuList.add(new Student(++Utils.stuId,name,sex,birthday));

        System.out.println("【成功】学员信息添加成功！");

    }
}
