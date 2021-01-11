package CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 请使用CyclicBarrier编写一个程序。
 * <p>
 * l 定义第一个线程：计算1--10000的所有数的累加和。
 * <p>
 * l 定义第二个线程：计算1--10000的所有偶数的累加和。
 * <p>
 * l 定义第三个线程：计算1--10000的所有奇数的累加和。
 * <p>
 * l 定义第四个线程：打印：”计算完毕”。
 * <p>
 * 要求在“第一个线程”、“第二个线程”、“第三个线程”全部执行完毕，再执行“第四个线程”。
 */
public class test01 {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
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
            public void run(){
                int sum = 0;
                for (int i = 1; i <= 10000; i++) {
                    if (i%2==1){
                        sum+=i;
                    }
                }
                System.out.println(Thread.currentThread().getName()+"奇数和为："+sum);
                try {
                    barrier.await();
                }catch (InterruptedException|BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
