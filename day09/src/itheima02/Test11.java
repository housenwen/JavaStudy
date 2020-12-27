package itheima02;

import java.util.Scanner;

public class Test11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");
        String s = scanner.nextLine();
        String [] sArr  = s.split(",");

        Student stu = new Student();
        stu.setName(sArr[0]);
        stu.setAge(sArr[1]);

        System.out.println(stu.getName()+"..."+stu.getAge());

    }
}
