package itheima;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        SimpleDateFormat sdf = new SimpleDateFormat(("yyyy-MM-dd"));

        System.out.println("请输入您的生日（yyyy-MM-dd）");
        String datestr = sc.nextLine();

        Date birthday = sdf.parse(datestr);
        Date now = new Date();

        if (birthday.after(now)){
            System.out.println("生日必须早于当前日期");
            return;
        }

        long millisecond = now.getTime()- birthday.getTime();

        long days = millisecond/1000/60/60/24;

        System.out.println("您来到这个世界已经："+days+"天");


    }
}
