package itheima03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class text {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        //接收用户输入
        System.out.println("请输入您的生日（yyyy-MM-dd）");
        String dateStr = sc.nextLine();
        System.out.println("请输入您的生日（yyyy-MM-dd）");
        String dateStr2 = sc.nextLine();

        //创建SimpleDateFormat对象
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //判断日期是否正确
        //异常直接抛
        Date birthday = sdf.parse(dateStr);
        Date birthday2 = sdf.parse(dateStr);

        Date now = new Date();
        if (birthday.after(now)) {
            System.out.println("生日必须早于当前日期！");
            return; //结束程序
        }


        //计算来到世界xx天
        long millisecond = now.getTime() - birthday.getTime();

        long days = millisecond / 1000 / 60 / 60 / 24;

        long months = millisecond/ 1000 / 60 / 60 / 24/30;

        long years=millisecond/ 1000 / 60 / 60 / 24/365;

        System.out.println("您已经来到这个世界" + days + "天");
        System.out.println("您已经来到这个世界" + years + "年");
        System.out.println("您已经来到这个世界" + months + "月");


    }
}
