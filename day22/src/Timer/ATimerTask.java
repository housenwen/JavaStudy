package Timer;

import java.util.Timer;
import java.util.TimerTask;

public class ATimerTask extends TimerTask {

    public static Timer t = new Timer();;

    @Override
    public void run() {
        for (int i = 10; i > 0; i--) {
            System.out.println("倒数："+i);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("原子弹轰炸东京！！！");

        t.cancel();

    }
}
