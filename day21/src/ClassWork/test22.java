package ClassWork;

import java.util.Scanner;

public class test22 {
    public static void main(String[] args) {
        String[] arr = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("请输入一个1-7的值:");
        int num = scanner.nextInt();
        //从数组中取出对应的星期几
        try{
            String week = arr[num - 1];
            System.out.println(week);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("输入错误！！");
        }
    }}
}
