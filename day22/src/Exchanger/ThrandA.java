package Exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ThreadA extends Thread {
    private Exchanger<String> exchanger;
    public ThreadA(Exchanger<String> exchanger) {
        super();
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            System.out.println("线程A欲传递值'礼物A'给线程B，并等待线程B的值...");
            System.out.println("在线程A中得到线程B的值=" + exchanger.exchange("礼物A",5, TimeUnit.SECONDS));
            System.out.println("线程A结束！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (TimeoutException e){
            System.out.println("5秒钟没等到线程B的值，线程A结束！");
//            e.printStackTrace();
        }
    }

}
