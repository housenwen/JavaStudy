package Callable;

import java.util.Random;
import java.util.concurrent.*;

public class work4 {
    static class AvgCallable implements Callable {

        @Override
        public Object call() throws Exception {
            Random r = new Random();
            int count = 10;
            double sum = 0;
            for (int i = 0; i < count; i++) {
                int index = r.nextInt(100) + 1;
                sum += index;
            }
            double avg = sum / count;

            System.out.println(Thread.currentThread().getName() +"线程的平均数为："+ avg);

            return avg;

        }
    }

    public static void main(String[] args) {

        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        Future<Double> f1 = threadPool.submit(new AvgCallable());
        Future<Double> f2 = threadPool.submit(new AvgCallable());
        Future<Double> f3 = threadPool.submit(new AvgCallable());

        try {

            double avg = (f1.get() + f2.get() + f3.get()) / 3;
            // %d 整数
            // %s 字符串
            // System.out.println(result);
            System.out.println("三个线程的平均数为：" + String.format("%.2f", avg));

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();

    }
}
