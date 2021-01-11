package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PersonThread extends Thread {
    private CyclicBarrier cbRef;

    public PersonThread(CyclicBarrier cbRef) {
        this.cbRef = cbRef;
    }
//    Lock lock = new ReentrantLock();

    final Object lock = new Object();

    @Override
    public void run() {
//        lock.lock();
        synchronized (lock) {
            try {
                Thread.sleep((int) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName() + " 到了! ");
                cbRef.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
//        lock.unlock();
        }
    }
}