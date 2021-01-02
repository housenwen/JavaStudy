package itheima06;

import java.util.Scanner;

public class test {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        System.out.println("姓名：");
        String name = sc.next();
        System.out.println("年龄：");
        String age = sc.next();
        System.out.println("身高：");
        String height = sc.next();
        System.out.println("婚否：");
        String flag = sc.next();
        System.out.println("性别：");
        String sex = sc.next();

        int age1 = Integer.parseInt(age);
        double height1 = Double.parseDouble(height);
        boolean flag1 = Boolean.parseBoolean(flag);
        char sex1 = sex.charAt(0);

        Student s1 = new Student(name,age1,height1,flag1,sex1);

        System.out.println(s1);

    }






}
