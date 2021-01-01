package itheima03;

import java.util.Calendar;

public class test03 {
    public static void main(String[] args) {

        Calendar calendar =  Calendar.getInstance();

        System.out.println("年："+calendar.get(Calendar.YEAR));
        System.out.println("月："+calendar.get(Calendar.MONTH+1));
        System.out.println("日："+calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("时："+calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println("分："+calendar.get(Calendar.MINUTE));
        System.out.println("秒："+calendar.get(Calendar.SECOND));
//        System.out.println("周："+calendar.get(Calendar.DAY_OF_WEEK));

        int week = calendar.get(Calendar.DAY_OF_WEEK);

        weekGet(week);


    }

    private static void weekGet(int week) {

        String[] strings = {"周日","周一","周二","周三","周四","周五","周六"};

        System.out.println("这一天是："+strings[week-1]);

    }


}
