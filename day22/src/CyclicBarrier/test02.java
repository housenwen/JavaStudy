package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class test02 {
    public static void main(String[] args) {
        // 注意，题目要求在第四个线程中打印 计算完毕
        // 但 CyclicBarrier 实际是在前三个线程中，是最后一个将计数减为0的线程内调用这个 Runnable 的，因此严格的说，没有满足题目要求
        // 当然可以创建新的线程来打印 计算完毕，但显然不必要了
        CyclicBarrier barrier = new CyclicBarrier(3, new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"计算完毕");
            }
        });

        // 计算 1~10000 所有和的线程
        new Thread() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 1; i <= 10000; i++) {
                    sum += i;
                }
                System.out.println(Thread.currentThread().getName() + "计算结果为：" + sum);
                // 等等别人
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        // 计算 1~10000 所有偶数和的线程
        new Thread() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 1; i <= 10000; i++) {
                    if (i % 2 == 0) {
                        sum += i;
                    }
                }
                System.out.println(Thread.currentThread().getName() + "计算结果为：" + sum);
                // 等等别人
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        // 计算 1~10000 所有奇数和的线程
        new Thread() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 1; i <= 10000; i++) {
                    if (i % 2 == 1) {
                        sum += i;
                    }
                }
                System.out.println(Thread.currentThread().getName() + "计算结果为：" + sum);
                // 等等别人
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
