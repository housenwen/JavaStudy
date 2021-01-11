package Timer;


import java.util.*;

public class Test{
    public static void main(String[] args){
        //1.设置一个定时器，2秒后启动，只执行一次
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