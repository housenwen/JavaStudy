package Timer;

import java.util.*;

public class test02 {
    public static void main(String[] args) {
        //3.设置一个定时器，在2030年01月01日零时开始执行，每隔24小时执行一次
        Timer t3 = new Timer();
        Calendar c = new GregorianCalendar(2020, 1-1, 1, 0, 0, 0);
        Date d = c.getTime();

        t3.schedule(new TimerTask(){
            @Override
            public void run(){
                System.out.println("搜索全盘......");
            }
        },d,1000 * 3600 * 24);
    }
}
