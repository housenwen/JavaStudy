package Classwork03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Seller implements Runnable{

    private int ticket = 1000;

    final Lock lock = new ReentrantLock();

//    final Object lock = new Object();

    @Override
    public  void run() {
        sellTicket();
    }

    private  void sellTicket() {
        int sell = 0;

        while (true) {
            lock.lock();
//            synchronized (lock) {
                if (ticket > 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                    sell++;
                    ticket--;

                    System.out.println(Thread.currentThread().getName() + "已卖" + sell + "还剩" + ticket);

                } else {
                    break;
                }
            lock.unlock();
            }
    }
}
//}
