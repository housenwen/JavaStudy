package Classwork03;


import java.util.concurrent.*;

public class work2 {
    public static void main(String[] args) {
        // 创建线程池对象
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        // 提交线程任务
        Future<Double> f1 = threadPool.submit(new AvgCallable());
        Future<Double> f2 = threadPool.submit(new AvgCallable());
        Future<Double> f3 = threadPool.submit(new AvgCallable());

        try {
            double result = (f1.get() + f2.get() + f3.get())/3;
            System.out.println(String.format("三个数的平均数为：%.2f",result));
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}
