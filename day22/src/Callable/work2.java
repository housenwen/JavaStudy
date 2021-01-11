package Callable;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class work2 {

    final static Lock lock = new ReentrantLock();

//    final static Object lock = new Object();
    static int count = 0;
    static int flag = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 1000; i++) {

                    synchronized (lock) {

                        while (flag == 1) {
                            try {

                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (i % 2 == 0 && i % 3 == 0 && i % 5 == 0 && i % 7 == 0) {
                            System.out.println(i);
                            flag=1;
                            lock.notify();

                        }
                    }

                }

                synchronized (lock){
                    flag = 2;
                    lock.notify();
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                synchronized (lock) {
                    // 不断循环进行计数
                    while (true) {
                        // 还没轮到自己，休息
                        while (flag == 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (flag == 2) {
                            // 退出
                            break;
                        } else if (flag == 1) {
                            // 计数加1
                            count++;
                            // 唤醒遍历线程
                            flag = 0;
                            lock.notify();
                        }
                    }
                }
            }
        }.start();

        // 休息2秒，让两个线程全部执行完毕，打印“计数器”的结果
        Thread.sleep(2000);
        System.out.println(count);

    }
}
