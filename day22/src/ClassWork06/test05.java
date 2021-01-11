package ClassWork06;

import java.util.concurrent.CountDownLatch;

public class test05 {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始计算");
                try {
                    // Thread-0进入阻塞，等计数减为0
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"计算完毕");
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                int sum = 0;
                for (int i = 0; i <= 100; i++) {
                    sum+=i;
                }
                System.out.println(Thread.currentThread().getName()
                        +"进行计算，结果为："+sum);
                // 计数减为0，可以让那边阻塞的 Thread-0 恢复运行
                latch.countDown();
            }
        }.start();
    }
}
