package itheima03;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws ParseException {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入第一个生日（yyyy-MM-dd）:");

            String str = sc.nextLine();

            System.out.println("请输入第二个生日（yyyy-MM-dd）:");

            String str2 = sc.nextLine();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date birthday = sdf.parse(str);
            Date birthday2 = sdf.parse(str2);

            Date now = new Date();

            if (birthday.after(now)||birthday2.after(now)) {
                System.out.println("生日必须早于当前日期！");
//                return;//结束程序
            }

            else {

                long millisecond = now.getTime() - birthday.getTime();

                long days = millisecond / 1000 / 60 / 60 / 24;

                long millisecond2 = now.getTime() - birthday2.getTime();

                long days2 = millisecond2 / 1000 / 60 / 60 / 24;

                System.out.println("第一个生日已经来到这个世界" + days + "天");
                System.out.println("第二个生日已经来到这个世界" + days2 + "天");


                if(days>days2){

                    System.out.println("第一个生日早于第二个生日");
                }else if (days<days2){
                    System.out.println("第一个生日晚于第二个生日");
                }else {
                    System.out.println("恭喜你们是同年同月同日生");
                }

                break;

            }


        }

    }
}
