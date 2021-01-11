package Callable;

import Classwork.MyCallablle;

import java.util.concurrent.*;

public class test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        MyCallable my = new MyCallable(1);
        Future<Integer> f1 = pool.submit(my);
        Integer i1 = f1.get();
        System.out.println(Thread.currentThread().getName()+"结果"+i1);

        MyCallable m1 = new MyCallable(3);
        Future<Integer> f2 = pool.submit(m1);
        Integer i2 = f2.get();
        System.out.println(Thread.currentThread().getName()+"结果："+i2);

        MyCallable m2 = new MyCallable(1);
        Future<Integer> f3 = pool.submit(m2);
        Integer i3 = f3.get();
        System.out.println(Thread.currentThread().getName()+"结果"+i3);

        double avg = (f1.get()+f2.get()+f3.get())/3;


        System.out.println(Thread.currentThread().getName()+"平均值："+
               String.format("%.2f:",avg));

    }

    static class MyCallable implements Callable<Integer> {

        private int num;

        public MyCallable(int num) {
            this.num = num;
        }

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            int n =  num;

            for (int i = 0; i < n; i++) {
                sum+=n;
            }
            return sum;
        }
    }
}
