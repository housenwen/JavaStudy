package itheima03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class test02 {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入日期：yyyy-MM-dd");
        String str = scanner.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//将字符串日期转换成Date对象
        Date date = sdf.parse(str);



        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
//将Date对象转换成指定格式的字符串
        String datestr = sdf2.format(date);

        System.out.println(datestr);

        System.out.println("---------------------------");

        Date now = new Date();
        if (date.after(now)){
            System.out.println("日期必须早于当前日期");
            return;
        }

        long millisecond = now.getTime()-date.getTime();

        long days = millisecond/1000/60/60/24;

        System.out.println(millisecond);
        System.out.println(days);





    }
}
