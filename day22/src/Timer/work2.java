package Timer;

import java.util.TimerTask;
import java.util.Timer;

public class work2 {
    public static void main(String[] args) {
        Timer t = new Timer();
        t.schedule(new TimerTask(){
            @Override
            public void run(){
                for(int i = 10;i >= 0 ; i--){
                    System.out.println("倒数：" + i);
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){}
                }
                System.out.println("嘭......嘭......");
                //任务执行完毕，终止计时器
                t.cancel();
            }
        },2000);
    }
}
