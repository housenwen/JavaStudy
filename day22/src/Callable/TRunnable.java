package Callable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TRunnable implements Runnable{

    private int num = 1000;
//    Lock lock = new ReentrantLock();
    Object obj = new Object();

    @Override
    public  void run() {
        int count = 0;
        String name = Thread.currentThread().getName();

            while (true){

                synchronized (obj){
                if (num>0){
                    String dc = DoubleColorBallUtil.create();
                    System.out.println("编号："+num+"从"+name+"拿到的票号："+dc);
                    num--;
                    count++;
                    try {
                        Thread.sleep(0);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }else {
                    System.out.println("从"+name+"进入"+count+"人");
                    break;
                }

            }


        }

    }
}
