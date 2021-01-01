package itheima03;

import java.util.Calendar;

public class test04 {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        calendar.set(1949,9,1);

        int week = calendar.get(Calendar.DAY_OF_WEEK);

        System.out.println(week);

        weekGet(week);

    }

    private static void weekGet(int week) {

        String[] strings = {"周日","周一","周二","周三","周四","周五","周六"};

        System.out.println("这一天是："+strings[week-1]);

    }
}
