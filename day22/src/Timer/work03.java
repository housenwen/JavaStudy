package Timer;

import java.util.*;
//3.设置一个定时器，在2030年01月01日零时开始执行，每隔24小时执行一次
public class work03 {
    public static void main(String[] args) {
        Timer t = new Timer();
        Calendar calendar = new GregorianCalendar(2021, 1-1,
                9, 15, 48, 0);
        Date d = calendar.getTime();
        t.schedule(new TimerTask() {
            int i = 1;
            @Override
            public void run() {
                System.out.println("搜索全盘......"+(i++)+"次");
            }
        },d,1000);
    }
}
