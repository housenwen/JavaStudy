package ClassWork;

import java.util.concurrent.atomic.AtomicInteger;

public class work3 {

        // 用原子整数类保证对 i 运算的原子性
        static AtomicInteger i = new AtomicInteger();

        public static void main(String[] args) throws InterruptedException {
            // t1 线程让 i 自增 50000 次
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 50000; j++) {
                        i.incrementAndGet();
                    }
                }
            };

            // t2 线程让 i 自增 50000 次
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 50000; j++) {
                        i.incrementAndGet();
                    }
                }
            };

            t1.start();
            t2.start();
            t1.join(); // main 线程等待 t1 运行结束
            t2.join(); // main 线程等待 t2 运行结束
            System.out.println(i);
        }
    }

