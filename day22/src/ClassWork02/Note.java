package ClassWork02;

import java.util.concurrent.locks.ReentrantLock;

public class Note implements Runnable {

    private int note = 1000;

    final ReentrantLock lock = new ReentrantLock();


    @Override
    public void run() {

        int sellNote = 0;

        while (true) {

            lock.lock();
            if (note > 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                note--;
                sellNote++;

                System.out.println(Thread.currentThread().getName()+"已卖"+sellNote+"还剩"+note);
            }else {
                break;
            }lock.unlock();
        }
    }
}
