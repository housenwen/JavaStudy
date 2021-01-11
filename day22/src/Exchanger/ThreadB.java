package Exchanger;

import java.util.concurrent.Exchanger;

public class ThreadB extends Thread {

    private Exchanger<String> exchanger;
    public ThreadB(Exchanger<String> exchanger) {
        super();
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        try {
            System.out.println("线程B欲传递值'礼物B'给线程A，并等待线程A的值...");
            System.out.println("在线程B中得到线程A的值=" + exchanger.exchange("礼物B"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
