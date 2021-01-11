package Timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class test01 {
    public static void main(String[] args) {
        //2.设置一个定时器，5秒后开始执行，每一秒执行一次
        Timer t2 = new Timer();
        t2.schedule(new TimerTask(){
            @Override
            public void run(){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(sdf.format(new Date()));
            }
        },5000,1000);
    }
}
