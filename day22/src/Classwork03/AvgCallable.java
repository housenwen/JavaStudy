package Classwork03;

import java.util.Random;
import java.util.concurrent.Callable;

public class AvgCallable implements Callable<Double> {


    @Override
    public Double call() throws Exception {

        Random r = new Random();

        int count = 10;

        double sum = 0;

        for (int i = 0; i < count; i++) {
            sum += r.nextInt(100) + 1;
        }
        double avg = sum/count;

        System.out.println(Thread.currentThread().getName()+"线程生成的平均数："+avg);

        return avg;

    }
}
