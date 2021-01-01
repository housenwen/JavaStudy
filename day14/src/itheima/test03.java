package itheima;

import java.util.Date;

public class test03 {

    public static void main(String[] args) {

        Date date = new Date();

        System.out.println(date);

        System.out.println(new Date(0L)); // Thu Jan 01 08:00:00 CST 1970


//        public long getTime();  获取的是日期对象从1970年1月1日 00:00:00到现在的毫秒值
        System.out.println(date.getTime());
        System.out.println(date.getTime() * 1.0 / 1000 / 60 / 60 / 24 / 365 + "年");

        //public void setTime(long time):设置时间，给的是毫秒值
//        long time = 1000*60*60*24*366;
        long time = System.currentTimeMillis();

        date.setTime(time);

        System.out.println(date);

    }


}
