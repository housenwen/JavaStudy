package itheima;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class test {

    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        System.out.println("请输入日期:(yyyy-MM-dd)");

        String str = sc.nextLine();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date date = sdf.parse(str);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");

        String newDate = sdf1.format(date);

        System.out.println(newDate);


    }

}
