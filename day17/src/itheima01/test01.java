package itheima01;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test01 {
    public static void main(String[] args) throws ParseException {
        //格式化：从 Date 到 String
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String s = sdf.format(d);
        System.out.println(s);
        System.out.println("--------");

        //从 String 到 Date
        String ss = "2048-08-09 11:11:11";
        String ss1 = "2048-08-09 11:11:11";
        //ParseException
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



        Date dd = sdf2.parse(ss);
        Date dd2 = sdf2.parse(ss1);

        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

        String s1 = sdf3.format(dd2);

        System.out.println(dd);
        System.out.println(s1);


    }
}
